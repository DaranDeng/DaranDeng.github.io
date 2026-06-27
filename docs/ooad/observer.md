# 设计模式简介（Intro to Design Patterns）

## 问题1：什么是设计模式？
设计模式是软件设计中**常见问题的典型解决方案**。

它不是可以直接复制到程序中的现成函数或库，而是一个**通用概念**（像蓝图），你可以根据自己程序的实际情况去实现它。

> 课件原话："The pattern is not a specific piece of code, but a general concept for solving a particular problem."

## 问题2：设计模式和算法有什么区别？
| | 算法 | 设计模式 |
|---|---|---|
| 特点 | 定义一组明确的步骤 | 更高层次的方案描述 |
| 类比 | 食谱（精确步骤） | 蓝图（看得到结果和特征，但实现顺序由你决定） |

同一个设计模式在不同程序中可以产生**不同的代码**。

## 问题3：设计模式包含哪四个组成部分？

1. **Intent**：问题和解决方案的简短描述
2. **Motivation**：问题及模式如何解决的详细解释
3. **Structure**：模式的各部分及其关系
4. **Code example**：用编程语言帮助理解模式思想

## 学习设计模式的一个警告是什么？
**"If all you have is a hammer, everything looks like a nail."**

初学者学完几个模式后，容易**到处套用**，即使简单代码就能解决问题的地方也用模式。

---

# Observer 设计模式（Observer Design Pattern）

## 问题7：Observer 模式要解决什么核心问题？
**解耦 publisher 和 subscribers**，避免：

- subscriber 反复轮询（浪费时间）
- publisher 通知了不感兴趣的人（浪费资源）

**课件例子**：Customer 想买新款 iPhone，Store 即将到货。

- 坏方案1：Customer 每天去店里看 → 浪费时间
- 坏方案2：Store 每次到货给所有顾客发邮件 → 骚扰不感兴趣的人
- **Observer 方案**：Customer 订阅通知，Store 到货时只通知订阅者

## 问题8：publisher 和 subscriber 分别是什么？

- **Publisher（发布者）**：状态变化时会通知其他对象的对象。也叫 **subject**
- **Subscriber（订阅者）**：想追踪 publisher 状态变化的对象

## 问题9：订阅机制（subscription mechanism）包含哪两部分？

1. 一个**数组字段**，存储 subscriber 对象的引用列表
2. 几个**公有方法**，允许添加和移除 subscriber

## 问题10：天气监测系统的例子中，谁扮演什么角色？

- **RainSensor**：publisher（发布者）
- **WeatherSystemDisplay** 和 **NationalWeatherSystem**：subscribers（订阅者）
- 流程：RainSensor 每12小时读取数据 → 通知所有订阅者 → 订阅者调用 `ReceiveWeatherReading()`

## 问题11：Observer 模式的适用场景是什么？

1. 一个对象的状态变化**可能需要改变其他对象**
2. 这些对象的**集合事先不知道**或**动态变化**
3. 例子：GUI 按钮，让客户可以在按钮被按下时挂载自定义代码

## 问题12：Observer 模式的优缺点？
| 优点 | 缺点 |
|---|---|
| **开闭原则**：可以引入新 subscriber 类而不改变 publisher 代码 | Subscriber 被通知的**顺序是随机的** |
| 可以在**运行时**建立对象之间的关系 | |

## 问题13：如何实现 Observer 模式？（4步）

1. 将业务逻辑拆分为：**publisher**（核心功能）和 **subscriber 类**（其余部分）
2. 声明 **subscriber 接口**，至少包含一个 `update` 方法
3. 声明 **publisher 接口**，包含添加/移除 subscriber 的方法
4. 创建**具体 publisher 类**（事件发生时通知所有 subscriber）和**具体 subscriber 类**（实现 update 方法）
