# 流水线与超标量处理器（Pipelining & SuperScalar）


## 一、分支预测：为什么要提前猜测？


### 问题场景
```assembly
cmp x, 0
jg label1    ; 如果大于，则跳转到 label1
mov a, 2
jmp end
label1:
mov a, 1
end:
```

**高级语言对应：**
```c
if (x > 0) {
    a = 1;
} else {
    a = 2;
}
```


### 为什么需要猜测？


在流水线中，当指令执行顺序如下时：

| 时间 | 指令 1（cmp） | 指令 2（jg） | 指令 3（？） |
| :--- | :--- | :--- | :--- |
| T1 | IF | | |
| T2 | ID | IF | |
| T3 | EX | ID | **IF ← 这里要取指！** |

**关键问题：**
- 指令 2（`jg`）在 **T3 周期** 才到 `EX` 阶段，此时才知道是否跳转
- 但指令 3 在 **T3 周期** 就要执行 `IF`（取指）
- CPU 必须决定：下一条是 `mov a, 2` 还是 `mov a, 1`？


### 解决方案：分支预测（Branch Prediction）

- CPU 维护**分支历史表 / 跳转预测表（branch prediction table / BTB）**，记录每条分支过去通常是“跳”还是“不跳”，以及跳到哪里。 [xiaohui](https://www.xiaohui.com/dev/mmx/mmx_p_22_1.htm)
- 取指阶段看到分支指令时，**根据历史直接预测方向和目标地址并开始取指**。
- 猜对了：流水线继续高效运行 ✅
- 猜错了：把错误路径上的指令 **flush（冲刷）** 掉，重新从正确地址取指 ❌，付出几拍分支误预测代价，但整体仍比每次都停在分支处要高效。 [xiaohui](https://www.xiaohui.com/dev/mmx/mmx_p_22_1.htm)


***


## 二、流水线性能分析


### 关键参数


| 符号 | 含义 | 说明 |
| :--- | :--- | :--- |
| **C** | 单条完整指令的总执行时间 | 无流水线时的一条指令执行时间 |
| **n** | 流水线阶段数 | 如 5 级、10 级、20 级 |
| **C/n** | 流水线每个阶段的耗时 | 即**时钟周期** |

无流水线时，执行 k 条指令大约需要 \(k \times C\) 时间； [geeksforgeeks](https://www.geeksforgeeks.org/computer-organization-architecture/computer-organization-and-architecture-pipelining-set-1-execution-stages-and-throughput/)
有流水线且装满后，理想情况下每个周期完成 1 条指令，总时间大约是 \(C + (k-1)\times C/n\)（前面一段是“装满时间”）。 [geeksforgeeks](https://www.geeksforgeeks.org/computer-organization-architecture/computer-organization-and-architecture-pipelining-set-1-execution-stages-and-throughput/)


### 为什么 n 要尽量大？


**优点：**
1. 阶段越多，每个阶段工作量越少 → 时钟周期缩短 → 主频提高（`频率 = 1/周期`）。 [diveintosystems](https://diveintosystems.org/book/C5-Arch/pipelining.html)
2. 理想情况下，流水线装满后可以做到**每个时钟周期完成一条指令**，吞吐量大幅提升（虽然单条指令的总延迟 C 变化不大）。 [en.wikipedia](https://en.wikipedia.org/wiki/Instruction_pipelining)

**限制：**
- 每级之间需要流水线寄存器，会引入额外延迟和功耗。
- 级数越多，**分支预测错误时需要冲刷的指令越多，惩罚更大**。 [en.wikipedia](https://en.wikipedia.org/wiki/Instruction_pipelining)
- 数据冒险更频繁，对旁路网络、调度逻辑要求更高。
- 现代处理器通常使用 **约 10~20 级** 的流水线作为折中。 [en.wikipedia](https://en.wikipedia.org/wiki/Instruction_pipelining)


***

## 三、指令分配与指令级并行（ILP）


### 顺序执行 vs 流水线执行（复习）


- **非流水线（顺序）处理器**：一条指令依次完成 IF → ID → EX → MEM → WB，全部结束后下一条才能开始 → **一条一条做完**，吞吐量低。 [geeksforgeeks](https://www.geeksforgeeks.org/computer-organization-architecture/computer-organization-and-architecture-pipelining-set-1-execution-stages-and-throughput/)
- **流水线处理器**：将每条指令拆成多个阶段，在同一个时刻不同指令位于不同阶段 → **多条指令不同阶段并行**，整体吞吐量高。 [diveintosystems](https://diveintosystems.org/book/C5-Arch/pipelining.html)


### 超标量处理器的指令分配策略

```assembly
指令 1: ADD R1, R2, R3    ; R1 = R2 + R3
指令 2: SUB R4, R1, R5    ; R4 = R1 - R5  （依赖 R1）
指令 3: MUL R6, R7, R8    ; R6 = R7 × R8  （无依赖）
```

**分配规则：**

| 指令对 | 依赖关系 | 能否同时发射 | 原因 |
| :--- | :--- | :--- | :--- |
| 指令 1 & 指令 2 | ✅ 有依赖（RAW） | ❌ 不能 | 必须等指令 1 生成并写出结果后才能执行指令 2 |
| 指令 1 & 指令 3 | ❌ 无依赖 | ✅ 能 | 可以同时发射到两条流水线并行执行 |

超标量（Superscalar）= **每个周期可以 issue 多条指令**，前提是它们在数据和资源上是独立的。 [en.wikipedia](https://en.wikipedia.org/wiki/Instruction_pipelining)


### 常见误解澄清


| 误解 | 正确理解 |
| :--- | :--- |
| "一条流水线只能同时搞一个指令" | ❌ 流水线的核心就是**同一时间处理多条指令的不同阶段** |
| "多条指令并行必须有多个流水线" | ❌ 一条超标量流水线就可以在一个时钟周期里同时开始多条指令，只要执行单元足够 |


***


## 四、Issue 与 Dispatch


### 定义与区别


| 概念 | 含义 | 发生位置 |
| :--- | :--- | :--- |
| **Issue（发射）** | 检查指令依赖、分配执行资源，将指令送入等待/保留队列 | 后端早期 |
| **Dispatch（派发）** | 当操作数就绪时，把指令真正送入具体执行单元 | Issue 之后，Execute 之前 |

在超标量/乱序处理器中，Issue 通常是批量接收指令并排队，Dispatch 负责在操作数准备好后把它们送往 ALU、Load/Store 单元等执行资源。 [cl.cam.ac](https://www.cl.cam.ac.uk/teaching/1718/OptComp/slides/lecture14.pdf)


### 在乱序执行中的角色

```
取指 → 译码 → 重命名 → [Issue] → [Dispatch] → 执行 → 写回
                    ↓             ↓
              检查依赖        发送操作数
              分配资源        到执行单元
```

**关键点：**
- Issue / Dispatch 通常在 **操作数读取（OF）之前或同时**进行。
- 都属于 **后端（Backend）** 的动作，是通往执行单元的入口。 [cl.cam.ac](https://www.cl.cam.ac.uk/teaching/1718/OptComp/slides/lecture14.pdf)


***


## 五、顺序执行 vs 乱序执行


### 对比


| 特性 | In-Order（顺序） | Out-of-Order（乱序） |
| :--- | :--- | :--- |
| **执行顺序** | 严格按程序顺序执行 | 只要数据就绪，指令可越过前面的未就绪指令先执行 |
| **Stall 处理** | 前面一条 stall，后面全部被拖住 | 后面独立指令可以绕过先执行 |
| **结果提交（Commit）** | 按执行顺序 | **仍按原程序顺序提交** |
| **实现复杂度** | 硬件简单 | 硬件复杂，需要 ROB、保留站、重命名等结构 |

### 重要概念区分

| 概念 | 含义 | 由谁决定 |
| :--- | :--- | :--- |
| **Program Order** | 指令在程序中的书写顺序 | 程序员 / 编译器 |
| **Data Dependency Order** | 由数据读写关系决定的必须保持的先后顺序 | 运算逻辑本身 |

乱序执行的目标是：**在不违反数据依赖顺序的前提下，尽量打破程序顺序，挖掘指令级并行度（ILP）**。 [cl.cam.ac](https://www.cl.cam.ac.uk/teaching/1718/OptComp/slides/lecture14.pdf)


***


## 六、数据依赖类型


### 三种依赖对比


| 类型 | 全称 | 含义 | 是否真依赖 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| **RAW** | Read After Write | 后面的指令要读前面指令写的结果 | ✅ **真依赖** | `ADD R1...` → `SUB ...,R1,...` |
| **WAR** | Write After Read | 后面的指令写，前面的指令读同一寄存器 | ❌ 假依赖（名字冲突） | 乱序时后令提前写可能破坏前令读 |
| **WAW** | Write After Write | 两条指令都写同一寄存器 | ❌ 假依赖（名字冲突） | 写顺序乱了会影响最终值 |

### 为什么 WAR/WAW 是“假”依赖？


**本质：** 只是“寄存器名字冲突”，并不是在传递真正的数据值。

**解决方案：寄存器重命名（Register Renaming）** [en.wikipedia](https://en.wikipedia.org/wiki/Data_dependency)
- 为每条指令的目标寄存器分配不同的**物理寄存器**。
- 这样从硬件视角看，WAR / WAW 冲突被消除，只剩下真正表示值流动的 RAW 依赖。


***


## 七、处理器前端与后端


### Front-End（前端）：In-Order


**作用：** 连续不断地“喂”指令给后端。

**主要阶段：**
1. 指令预取（Instruction Fetch）
2. 指令译码（Instruction Decode）
3. 分支预测（Branch Prediction）
4. 指令缓存（Instruction Cache）

**关键点：**
- 目标：保持后端不会“饿死”，始终有足够的指令可执行。
- 分支预测错误 → 前端需要 flush，重新从正确 PC 取指，这是**控制冒险（Control Hazard）**的主要来源。 [xiaohui](https://www.xiaohui.com/dev/mmx/mmx_p_22_1.htm)


### Back-End（后端）：可以是 Out-of-Order


**作用：** 处理数据依赖、调度执行、完成运算。

**主要阶段：**
1. 操作数读取（Operand Fetch）
2. 指令执行（Execute）
3. 写回结果（Write Back）
4. 数据缓存访问（Data Cache / MEM）

**关键点：**
- 关注 RAW / WAR / WAW 等数据依赖与资源冲突。
- 出现数据冒险（尤其是 RAW）时，通过旁路、重命名、停顿等方式处理。 [en.wikipedia](https://en.wikipedia.org/wiki/Data_dependency)


***


## 八、Write Back 详解


### Write Back 不只是“写回”

| 内容 | 说明 |
| :--- | :--- |
| **更新寄存器文件** | 将执行结果写入目标寄存器（如 `R1 = result`） |
| **旁路（Bypass/Forwarding）** | 同时把结果直接送给等待该数据的后续指令，避免等到 WB 再读 |
| **更新 ROB/保留站状态** | 标记该指令已完成，结果可提交 |
| **❌ 不是更新缓存块** | Data Cache 的更新属于 MEM 阶段，与 WB 分开 |

旁路是**解决 RAW 冒险的关键硬件技术**之一，可以减少或消除流水线停顿。 [en.wikipedia](https://en.wikipedia.org/wiki/Data_dependency)


***


## 九、冒险（Hazard）类型总结


### 按原因分类

| 冒险类型 | 发生位置 | 原因 | 解决方案 |
| :--- | :--- | :--- | :--- |
| **结构冒险（Structural）** | 前端或后端 | 硬件资源（端口、存储体）不足 | 增加端口、分离指令/数据缓存、调度 |
| **分支冒险（控制冒险）** | **前端** | 分支目标/方向未知，无法确定下一条指令 | 分支预测、延迟分支、减少分支（例如循环展开） |
| **数据冒险（RAW/WAR/WAW）** | **后端** | 操作数未就绪，或名字冲突 | 旁路、寄存器重命名、编译器指令调度、停顿 |


***


## 十、深入理解 RAW 冒险


### 为什么 RAW 既是正确的依赖，又会造成冒险？


#### 1. RAW 是程序逻辑的正确要求

```assembly
指令 1: ADD R1, R2, R3    ; R1 = R2 + R3  (写 R1)
指令 2: SUB R4, R1, R5    ; R4 = R1 - R5  (读 R1)
```

**程序语义：** 必须先执行指令 1（写 R1），才能执行指令 2（读 R1），这是**真依赖（True Dependency）**。


#### 2. 但在流水线中会出问题（无旁路）

```
时间轴（时钟周期）：
       T1    T2    T3    T4    T5    T6
指令 1: IF → ID → EX → MEM → WB
指令 2:      IF → ID → OF → EX → MEM → WB
                          ↑
                     这里要读 R1！
```

- 指令 2 在 **T4（OF 阶段）** 要读 R1。  
- 指令 1 要到 **T5（WB 阶段）** 才把结果写入 R1。  
- 结果：指令 2 会读到**旧值** → 出现 RAW 冒险。 [en.wikipedia](https://en.wikipedia.org/wiki/Data_dependency)


#### 3. 解决方案：旁路（Forwarding/Bypassing）

```
时间轴（带旁路）：
       T1    T2    T3    T4    T5
指令 1: IF → ID → EX → MEM → WB
               │          ↓
               └────→ 旁路 ──┘
                        ↑
                   直接传给指令 2
指令 2:      IF → ID → OF → EX → MEM
```

**原理：**
1. 指令 1 在 EX 阶段就有结果。
2. 不等到 WB 再写寄存器，而是通过旁路网络**直接送到指令 2 的 EX/OF 输入**。
3. 指令 2 可以在不插入或少插入停顿的情况下使用正确结果。  
必要时，硬件仍会插入 stall（比如 load‑use hazard）。 [geeksforgeeks](https://www.geeksforgeeks.org/computer-organization-architecture/computer-organization-and-architecture-pipelining-set-1-execution-stages-and-throughput/)


***


## 十一、深入理解 WAR 冒险


### 疑问：读后写（WAR）本身看起来没问题，为什么说是冒险？


#### 顺序执行时看起来没问题

```assembly
指令 1: SUB R4, R1, R5    ; R4 = R1 - R5  （读 R1）
指令 2: ADD R1, R2, R3    ; R1 = R2 + R3  （写 R1）
```

程序写法是“先读 R1 再写 R1”，顺序执行不会错，但是在**乱序执行**时会出问题。


#### 乱序执行中的 WAR 冒险

- 如果硬件让**写 R1 的指令 2 提前执行并写回**，而读 R1 的指令 1 还没读完，那么指令 1 会读到“新值”，破坏语义。  
- 这就是 WAR 冒险 → 是“名字冲突”导致的假依赖。

**解决方案：寄存器重命名** [en.wikipedia](https://en.wikipedia.org/wiki/Data_dependency)
- 给指令 2 分配一个新的物理寄存器 P（而不是复用旧的 R1 对应的物理寄存器）。  
- 指令 1 继续读旧的物理寄存器，不受影响。  
- 这样，即使执行顺序乱了，逻辑上的读写关系仍然正确。


***


## 十二、DAG（有向无环图）


**作用：** 描述指令间的数据依赖结构，用于编译器和硬件的调度分析。 [cl.cam.ac](https://www.cl.cam.ac.uk/teaching/1718/OptComp/slides/lecture14.pdf)

- 节点：指令。
- 有向边：数据依赖（通常是 RAW）。
- 无环：表示不存在逻辑上的循环依赖，可以按拓扑序重排执行顺序。


***


## 十三、编译器优化与流水线：Loop Unrolling 与指令调度


### 1. Loop Unrolling（循环展开）


**目标：**  
- 减少循环控制开销（条件判断 + 跳转次数）。  
- 提供更多连续的计算指令，方便流水线和超标量发射，减少分支冒险影响。 [teach-sim](https://teach-sim.com/wp-content/uploads/2021/07/investigating-instruction-pipelining.pdf)

**效果：**
- 分支指令出现得更少 → 分支预测错误的机会更少 → 冲刷流水线次数减少。  
- 循环体变长，编译器可以在更大范围内重排指令，填补数据冒险造成的“空洞”（stalls）。 [teach-sim](https://teach-sim.com/wp-content/uploads/2021/07/investigating-instruction-pipelining.pdf)


### 2. 编译器重新排列指令（Instruction Scheduling）

**目标：** 在不改变程序语义的前提下，通过调整指令顺序来**减少数据冒险导致的停顿**。 [cl.cam.ac](https://www.cl.cam.ac.uk/teaching/1718/OptComp/slides/lecture14.pdf)

**基本思路：**
- 分析依赖图（DAG），找出彼此独立的指令。
- 当某条指令因为 RAW hazard 需要等待结果时，把独立指令移动到中间执行，填补本来要 stall 的位置。

**例（逻辑示意）：**

原始顺序（第二条依赖第一条的 load 结果）：
1. `R1 = load [A]`
2. `R2 = R1 + 1`   ← 依赖 R1，可能需要等待内存
3. `R3 = ...`      ← 与 R1/R2 无关

优化后顺序：
1. `R1 = load [A]`
2. `R3 = ...`      ← 移到中间，利用等待时间
3. `R2 = R1 + 1`

这样就减少了需要硬件插入 stall 的周期，提高流水线利用率。 