---
layout: default
title: I/O Improvements
---

# I/O 改进与控制方式

## 1. 为什么 I/O 要单独设计?

**问题**

- 为什么外设不能直接接到系统总线上?
- 为什么需要 I/O chip / controller?

**答案**

- 外设种类多，速度差异大
- 协议和电气特性不同
- CPU / 内存和外设之间需要一个中间层做适配

[Figure 1: I/O chip 在 CPU / memory / device 之间的位置]

## 2. 四种常见控制方式

- Polling 轮询
- Interrupt-driven I/O 中断驱动
- DMA Direct Memory Access 直接内存访问
- Channel control 通道控制

### 轮询 Polling
CPU 不停查状态位，简单但浪费 CPU。

### 中断 Interrupts
设备准备好后主动通知 CPU，效率比轮询高，但每次中断有保存 / 恢复现场开销。

### DMA
DMA controller 负责 CPU 和 memory 之间的大块数据搬运，CPU 只负责初始化和收尾。

### Channel control
更复杂的 I/O 组织方式，常见于更高端系统。

## 3. 轮询和中断的核心例子

### 轮询打印 `WORD`

**流程**

1. CPU 读状态位
2. 没准备好就继续等
3. 准备好后写一个字符
4. 重复直到完成

**问题**

- CPU 大量时间都花在等待和转数据上

### 中断输入一个字符 `H`

**流程**

1. 设备收到输入
2. 发出 interrupt request
3. CPU 暂停当前程序并保存现场
4. 执行 ISR interrupt service routine
5. 从设备读字符，写回主存缓冲区
6. 恢复现场继续运行

[Figure 2: 轮询 vs 中断流程]

**考点**

- 轮询：简单但低效
- 中断：更高效，但开销在每次中断的保存 / 恢复

## 4. DMA 的核心

DMA 的本质是：由 DMA controller 代替 CPU 完成主存和 I/O 之间的数据搬运。

**CPU 需要做的事**

- 设定传输方向
- 指定内存起始地址
- 指定数据长度
- 指定相关 I/O device

**为什么常按 word 传输?**

- 和总线数据宽度匹配
- 提高单次传输效率

## 5. Buffering 缓冲

**核心问题**
为什么要 buffer?

**答案**
让 I/O 和计算 overlap，减少互相等待。

### Single buffer

- 读一个块到 buffer
- 再复制到进程内存
- CPU 和 I/O 不能充分重叠

### Double buffer

- 一个 buffer 在计算，另一个 buffer 同时接收下一块数据
- 更容易实现重叠
- 对连续流式 I/O 特别有用

[Figure 3: single buffer vs double buffer]

**考点**

- 机械硬盘机械臂切换会让“同时读写”并不真的同时
- 缓冲区是中转站，不是最终数据目的地

