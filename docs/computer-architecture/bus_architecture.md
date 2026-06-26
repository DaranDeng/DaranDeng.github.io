---
title: bus architecture
---

# 总线架构 Bus Architectures

## 1. 总线是什么?

Bus is a pathway that inter-connects two or more components in a computer.

**关键词**
- Address bus
- Data bus
- Control bus

[Figure 1: 总线由三类信号线组成]

## 2. 为什么早期总线会变慢?

**原因**
- 设备越来越多
- 设备速度差异越来越大
- 广播式总线上的冲突和干扰越来越严重

**课件的答案方向**
- 分层总线 hierarchy
- 更快的 bus 靠近 CPU / cache
- 更慢的 bus 服务外设

## 3. 总线分层 Bus hierarchy

### 1980s 结构
- System bus
- Local bus
- Expansion bus

### 1990s 之后
- High-speed bus 作为中间层
- Bridge chip 连接不同层级

[Figure 2: bus hierarchy 演化图]

**考点**
- 会画出“CPU / cache / main memory / high-speed bus / expansion bus”的层次关系
- 会解释 bridge chip 的作用

## 4. 常见总线标准

### ISA
- 早期扩展总线
- 速度慢，但兼容性好

### PCI
- Peripheral Component Interconnect
- CPU independent
- 支持 bus master 和 arbitration
- 可以进行 block transfer

### AGP
- 专门为图形场景优化
- 点对点连接

### USB
- Universal Serial Bus
- hot-swappable
- 外设通用接口

### PCIe
- PCI Express
- lane-based serial point-to-point
- 取代 PCI / AGP 的主流方案

### SATA
- Serial ATA
- 面向存储设备

[Figure 3: PCI / AGP / USB / PCIe / SATA 的位置关系]

## 5. 总线仲裁 Arbitration

多个 bus master 同时请求总线时，需要 arbitration scheme。

常见策略：
- First-come-first-served
- Round robin
- Priority

**考点**
- 谁是 bus master
- 为什么 DMA 也能抢总线
- arbitration 是为了避免冲突

## 6. 现代趋势

**关键变化**
- 从 parallel bus 转向 serial point-to-point
- 从共享总线转向更细粒度的互连
- 从“一个大广播总线”转向“分层 + 交换网络”

**为什么?**
- 高速下 parallel bus 更容易受 skew / crosstalk 影响
- serial link 更容易做高速和长距离传输

