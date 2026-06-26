---
title: Cache
---

# 缓存 Cache

## 1. 为什么需要 Cache?

**问题驱动**
- 为什么 von Neumann bottleneck（冯·诺依曼瓶颈）会存在？
- 为什么 CPU 很快，程序还是会被 memory access 拖慢？

**答案**
主存 Main Memory 比 CPU 慢得多。CPU 计算一条指令可能只要少量 cycle，但一次主存访问通常更慢。  
所以系统把**频繁访问的数据**放到更靠近 CPU 的**缓存 Cache**里，减少等待时间。

[Figure 1: 冯·诺依曼瓶颈与 Cache 的引入]
- 课件核心意思：CPU 和主存之间存在明显速度鸿沟。
- 考点：标准答法是“缓存通过利用局部性原理，提高平均访存效率，缓解冯·诺依曼瓶颈”。

## 2. 为什么要多级存储器?

**Why multi-level memory?**
- 快的存储器贵、容量小
- 便宜的存储器慢、容量大
- 所以系统必须在 speed、capacity 和 cost 之间折中

**典型层次**
- CPU registers
- L1 cache
- L2 / L3 cache
- Main memory
- Secondary storage

[Figure 2: Multi-level memory hierarchy]
- 课件图想表达的是“越上层越快、越小、越贵”。
- 考点：要能把层次、速度、容量、成本三者关系讲清楚。

### Cache 也有多级

- **L1 cache**: 最快、最小、最贵，通常还分成
  - L1 i-cache (instruction cache, 指令缓存)
  - L1 d-cache (data cache, 数据缓存)
- **L2 / L3 cache**: 更大、更慢、更便宜

**思考题**
1. 为什么不把所有数据都放进 L1 cache?
2. 为什么有了 cache 还要 L2/L3?

**答案要点**
- L1 太小，放不下整个工作集 working set
- L2/L3 用来覆盖更大的局部性范围
- 设计目标是让大多数访问在更快的小 cache 中命中

## 3. 局部性原理 Locality of Reference

### 时间局部性 Temporal Locality
刚访问过的数据，短时间内很可能再次访问。

### 空间局部性 Spatial Locality
访问某个地址后，它附近的数据也很可能很快被访问。

[Figure 3: 局部性原理示意]
- 课件想强调：cache 一次装入的是 block / cache line，不是单个 byte。
- 考点：时间局部性、空间局部性是 cache 命中的根本原因，几乎必考。

## 4. Cache 如何工作?

**关键过程**
1. CPU 发出 memory access
2. Cache 先检查目标地址对应的数据是否已经在 cache 中
3. 如果在，叫 **cache hit**
4. 如果不在，叫 **cache miss**
5. miss 时从 main memory 取回整个 block，并复制到 cache

[Figure 4: Cache hit / miss 流程]
- hit: 数据直接从 cache 读出
- miss: 从主存取，再复制进 cache

**考点**
- hit / miss 的定义要会背
- miss 时“读主存 + 复制到 cache”不能漏
- cache 本质是“主存内容的副本”，不是另一份独立数据

## 5. Cache 的几个常考特征

- Cache 很小，通常小于主存的 1%
- Cache 很快，常常接近 1 cycle
- 从主存取数据时，会顺手拷贝一份到 cache
- cache 的本质是“用空间换时间”

**容易混淆点**
- Cache 不会直接加速 ALU 运算
- Cache 也不会改变主存的物理速度
- 它加速的是 fetch-execute cycle 里的访存部分

