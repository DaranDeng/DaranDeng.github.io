---
layout: default
title: 计算机组成原理总览
---

# Principles of Computer Composition 总览

## 模块主线

1. Memory hierarchy & Cache
2. Cache mapping / updating / coherence
3. Pipelining & Superscalar
4. I/O improvements
5. Bus architectures
6. GPGPU & GPU scheduling

## 期末常考的总逻辑

- **Cache** 解决 memory wall
- **Pipeline** 提高 instruction-level parallelism ILP
- **Superscalar / Out-of-order** 进一步挖掘并行度
- **I/O + DMA** 减少 CPU 等待外设
- **Bus hierarchy** 解决高速设备接入问题
- **GPU / SIMT** 解决大规模数据并行

## 考试答题模板

如果老师问“为什么要这样设计”，通常按这三步答：

1. 先说瓶颈是什么
2. 再说硬件怎么解决
3. 最后说代价是什么

## 关键词速记

- von Neumann bottleneck
- locality of reference
- cache hit / miss
- direct mapping / fully-associative / set-associative
- write-through / write-back
- coherence / MESI
- pipeline / hazard
- RAW / WAR / WAW
- branch prediction
- superscalar / out-of-order
- polling / interrupt / DMA
- bus hierarchy / arbitration
- warp / SIMT / branch divergence

