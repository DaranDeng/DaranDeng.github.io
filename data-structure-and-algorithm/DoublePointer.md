---
layout: default
---

# 代码随想录的双指针
+ 空指针是不能访问的，你在使用p->next->next隐含了p->next可能为空的风险，所以必须在之前检查p->next是否为空。
+ 两个链表，在未知长度谁大的情况下，直接赋予指针和某个变量代表大的意义。见力扣[https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/]
