# DaranDeng Notes

这是一份计算机科学自学笔记，目标不是做成完整百科，而是把课程学习、代码练习和阶段性理解整理成一条可以回看的路线。

站点结构参考 CSDIY 的阅读方式：顶部保持轻量，主要内容放在左侧目录里。你可以按学科顺序学习，也可以直接搜索某个主题。

## 怎么使用

- 想系统复习：从左侧目录依次阅读各学科的 Overview 页面。
- 想查某个知识点：使用顶部搜索，或在左侧学科树中定位。
- 想看代码练习：进入对应学科下的 examples 或源码目录。
- 想了解仓库历史：旧 Jekyll 页面、散图和二进制产物保存在 archive 中。

## 推荐路线

1. [Data Structures and Algorithms](ds-algorithm/index.md)
2. [Operating System](os/index.md)
3. [Computer Architecture](computer-architecture/index.md)
4. [Object-Oriented Analysis and Design](ooad/index.md)
5. [Engineering Notes](engineering-notes/index.md)

## 当前内容

| 模块 | 内容 |
| --- | --- |
| Data Structures and Algorithms | 题型笔记、复杂度、排序、双指针、滑动窗口、回溯、哈希与代码练习 |
| Operating System | API、进程、调度、并发、内存管理、磁盘与 RAID |
| Computer Architecture | Cache、流水线、总线、I/O、GPU 与课程总览 |
| OOAD | UML、类关系、包、SOLID、分层、序列图、威胁建模与设计模式 |
| Engineering Notes | 工程手记、工具记录和学习复盘 |

## 维护方式

新增笔记时，把 Markdown 放到对应的 `docs/<subject>/` 目录，并在 `mkdocs.yml` 的 `nav` 中加入入口。配套代码示例优先放在对应学科目录内，编译产物和历史杂物放到 `archive/`。
