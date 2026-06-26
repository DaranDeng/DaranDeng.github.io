---
title: Pipelining & SuperScalar
---

# 流水线 Pipelining 与 超标量 Superscalar

## 1. 为什么要流水线?

**问题**
- 一条指令为什么要拆成多个 stage?
- 为什么拆开后反而更快?

**答案**
把一条指令的执行过程拆成多个阶段后，可以让不同指令同时处在不同阶段执行。  
这样做提升的是**吞吐量 throughput**，不是单条指令的绝对延迟 latency。

[Figure 1: 无流水线 vs 有流水线]
- 课件核心图：多条指令在不同周期里占据不同 stage。
- 考点：流水线提升的是“每周期完成的指令数”，不是“每条指令更快完成”。

## 2. Instruction Cycle 的阶段

课件给出的阶段是：
- IF Instruction Fetch
- ID Instruction Decode
- OC Operand Calculation
- OF Operand Fetch
- EX Execute
- WO Write Operand

**考点**
- 记住这些阶段的英文原文
- 知道其中哪些偏 CPU internal operation，哪些会访问 memory / bus

[Figure 2: Instruction cycle 分阶段]

## 3. 流水线性能

若流水线有 `n` 个 stage，一条平均指令执行需要 `C` 个 cycle，则理想情况下：

```text
每隔 C / n 个 cycle 完成一条指令
```

**为什么 n 越大越好?**
- 每个 stage 工作量更小
- 时钟周期可以更短
- 主频可以更高

**但代价也更大**
- stage 之间需要 pipeline register
- 分支预测错误时，flush 成本更高
- data hazard 更频繁

## 4. Hazards

**Hazard** = 破坏流水线理想连续流的因素

### Structural hazard
硬件资源不够用。

### Control hazard
分支跳转导致下一条指令地址不确定。

### Data hazard
后一条指令依赖前一条指令结果。

[Figure 3: 三类 hazard]
- 考点：要能按“资源 / 控制流 / 数据流”三分法答题。

## 5. Data dependency

### RAW Read After Write
真依赖，最重要。

### WAR Write After Read
假依赖，名字冲突。

### WAW Write After Write
假依赖，名字冲突。

**考点**
- 顺序执行下 WAR/WAW 通常不是问题
- 乱序执行 out-of-order 时，重命名 register renaming 可以消除 WAR/WAW

## 6. Branch Prediction

**问题**
分支指令在执行前并不知道会跳还是不跳，那流水线怎么办?

**方法**
- Static prediction
- Dynamic prediction
- Branch target buffer BTB

**错了怎么办?**
错误路径上的指令要 flush，然后从正确路径重新取指。

[Figure 4: 分支预测与 flush]
- 考点：branch prediction 的目标就是减少 control hazard 造成的 bubble。

## 7. SuperScalar

**超标量 Superscalar**
每个 cycle issue 多条指令，但前提是这些指令之间资源独立、数据独立。

**题目里常考的点**
- 哪些指令可以同时发射
- RAW dependency 会阻止并行 issue
- Superscalar 不是“多条流水线的简单叠加”，而是更强的指令级并行 ILP 利用

## 8. 考试怎么问?

高频问法：
1. 流水线是什么，为什么能加速?
2. Hazard 分几类，分别怎么解决?
3. RAW / WAR / WAW 有什么区别?
4. 分支预测错了会怎样?
5. Superscalar 和普通 pipeline 的区别是什么?

