---
layout: default
title: GPGPU
---

# GPGPU 与 GPU 架构

## 1. 为什么 GPU 适合矩阵和图形?

**核心问题**
- 为什么 GPU 比 CPU 更适合图形渲染和矩阵运算?

**答案**
这些任务具有高度数据并行 data parallelism。  
GPU 用大量小核心同时处理很多相似的小任务。

[Figure 1: CPU vs GPU 的并行风格差异]

## 2. Warp 是什么?

**Warp**
- 通常由 32 个 threads 组成
- 一个 warp 内的 thread 遵循 SIMT single instruction, multiple threads

**Thread 的两个关键词**
- private register / local memory
- unique thread ID

## 3. SIMT 和 SIMD 的关系

SIMT = SIMD 的执行思想 + 多线程调度 + 标量编程模型

**意思**
- 程序员写的是 scalar 风格
- 硬件帮你做向量化执行

**考点**
- SIMT 不是简单等于 SIMD
- GPU 的线程调度和 CPU 的线程调度不同

## 4. Branch divergence

**问题**
warp 内线程如果走不同分支怎么办?

**答案**
GPU 会把分支路径拆开执行，没走当前路径的线程暂时不提交 architectural state。

[Figure 2: branch divergence 与 predicated execution]

**考点**
- branch divergence 会降低效率
- predicated execution 是 GPU 常见处理方式

## 5. 隐藏内存延迟

GPU 访存很慢，但它依靠大量 warp 轮换来隐藏 latency。

**思路**
- Warp A 等数据
- Warp Scheduler 立即切到 Warp B
- Warp B 执行计算
- 数据回来后再切回 Warp A

**考点**
- GPU 不是靠“大 cache”取胜
- 它更擅长“切换线程来掩盖等待”

## 6. GPU pipeline

GPU 里常见模块包括：
- Tensor Core
- FP Core
- INT Core
- SFU Special Function Unit
- Ld/St Unit
- Texture Unit

[Figure 3: GPU 层次结构与 pipeline]

**图形管线大致流程**
1. Vertex transform
2. Rasterization
3. Texture mapping
4. Shading
5. Final framebuffer output

## 7. 考试常问点

1. Warp 和 thread 的区别是什么?
2. SIMT 为什么适合 GPU?
3. 什么是 branch divergence?
4. GPU 如何隐藏 memory latency?
5. GPU pipeline 里各个 core 的作用是什么?

