---
layout: post  # 用主题的帖子布局，默认就好
title: "学新技术栈RISC-V的第一天：RISC-V 入门心得"  # 你的标题
date: 2025-10-08  # 今天日期
categories: [学习, RISC-V]  # 可选标签
---
# CS61C 2020 fall的RISC-V学习历程

今天我学了 React 的基础，遇到个坑：状态更新不即时。代码是这样的：

```jsx
function App() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```
