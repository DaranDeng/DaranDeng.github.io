---
layout: default
---

# 在操作系统的进程调度（Scheduling）中，评价调度算法好坏的指标通常分为两类：**面向用户的准则（User-Oriented Criteria）** 和 **面向系统的准则（System-Oriented Criteria）**。

## 两者对比表格

| 维度 | 面向用户 | 面向系统 |
|------|----------|----------|
| 关注主体 | 单个进程/用户 | 整体系统 |
| 典型指标 | 周转时间、响应时间、截止时间 | 吞吐量、CPU利用率、公平性 |
| 通常关系 | 互相冲突（如追求响应时间可能降低吞吐量） | 部分一致（如高吞吐利于CPU利用率） |

## 常见例子

- **先来先服务 (FCFS)**：对用户不友好（长进程阻塞短进程），但对系统简单、公平。
- **时间片轮转 (RR)**：响应时间好（面向用户），但上下文切换开销可能降低吞吐量（面向系统）。
- **短作业优先 (SJF)**：最小化平均周转时间（用户好），但可能导致长进程饥饿（系统公平性差）。

调度算法的选择，就是在这两类准则间权衡。

# CPU-bound & I/O-bound
limited by processing power or by external events(I/O)

# 调度

## 评估指标：
*用甘特图画*

- **average waiting time**: 所有进程等待时间片之和/进程数
- **overall time**：结束时刻-开始时刻

## Types (Levels) of Scheduling：

+ Long-term – decision to add to the pool of processes to be executed, e.g. admit a process onto Ready queue

+ Medium-term – decision to move process into main memory (e.g. from Suspend to Ready)

+ Short-term – decision is which process gets onto CPU next, e.g. move a process from Ready to Running

## 几种调度算法

1. **FCFS/FIFO/queueing**: ready queue and blocked queue，见课件图
   - non-pre-emptive。非抢占式，不会运行中途被替换
   - simple to implement
   - 可能等待很久

2. **Round Robin (RR，时间片轮转法)**: 只有ready和running两种状态
   - 抢占式
   - 运行一个时间片,quantum是什么？分配到每个进程的时间片。
   - 用户友好，可以运行多个进程
   - 优化:加一个阻塞队列，把复杂度从O(n)降到O(1)？

   1. 没有 Blocked Queue 时的 O(n) 问题（原始实现）

所有进程（Ready + Blocked）都在 同一个大队列 里。

      当 I/O 完成时：

      系统只知道“某个进程醒了”，但不知道它在队列的哪个位置。

      必须扫描整个队列找到该进程，把它移到队尾（或标记为 Ready）。

      扫描长度 = 所有进程数 → O(n)。

      这就是你说的“原始的没有阻塞队列”的情况。

   2. 有 Blocked Queue + 直接索引后的 O(1)
      现代实现（课件中后续的图已经隐含支持这种优化）：

      Ready Queue 和 Blocked Queue(s) 是独立的队列。

      每个进程的 PCB（Process Control Block）中：

      记录自己当前在哪个队列

      系统维护一个 PID → PCB 的数组/哈希表（直接索引）

      当 I/O 完成时（比如磁盘中断）：

      根据 PID 直接找到 PCB（O(1)）

      从对应的 Blocked Queue 中删除该进程（链表删除 O(1)）

      将该进程插入 Ready Queue 尾部（链表尾部插入 O(1)）

      全程不需要扫描任何队列 → O(1)

3. **SJF**
   - 必须先知道各进程的运行所需时间片，然后选择最短时间的先运行。
   - 主要问题是长进程可能寄饿
   - 抢占式

4. **shortest remaining time**
   - 真实情况中进程们并不是同时到达，那么如果采用FCFS算法，必须是按照到达的先后顺序来进行直到把某个进程运行完。但是采用SRT，可以减少average waiting time，显然抢占式

## 考点
 学会画各种调度算法的甘特图并根据图计算平均等待时间。注意：若某个进程A=3，说明到达时刻是3.那么之前的进程是已经运行了3个时间片。因为是从0时刻到达。
<img width="1106" height="803" alt="image" src="https://github.com/user-attachments/assets/e216b579-6081-431d-8309-8b0d7a2e5722" />
<img width="1092" height="562" alt="image" src="https://github.com/user-attachments/assets/4ee2adec-f103-44bf-89ae-6a69b4f92fa5" />
