---
layout: default
title: tutorial01
---

见(PDF文档)[file:///F:/%E6%9C%AC%E5%AD%A6%E6%9C%9F%E8%AF%BE%E4%BB%B6%E6%B1%87%E6%80%BB/2B/Tutorial%20Questions.pdf]
---

### **(c) 完整 BTB 访问记录表**

| Address of branch instruction (hex) | BTB hit or miss | Prediction from BTB (Taken or Not Taken) | Actual outcome of branch instruction |
|-------------------------------------|-----------------|------------------------------------------|--------------------------------------|
| 00124444 | M | Not Taken | Taken |
| 00124444 | H | Taken | Not Taken |
| 00230668 | M | Not Taken | Not Taken |
| 00410C6C | M | Not Taken | Taken |
| 00124444 | H | Not Taken | Not Taken |
| 00230668 | M | Not Taken | Not Taken |
| 00410C6C | H | Taken | Taken |
| 00124444 | H | Not Taken | Not Taken |
| 00124444 | H | Not Taken | Not Taken |
| 00230668 | M | Not Taken | Taken |
| 00230668 | H | Taken | Not Taken |

---

**说明：**
- **M**：BTB 未命中，预测为 **Not Taken**。  
  - 若实际 **Taken**，则分配新条目，历史位设为 **T**。  
  - 若实际 **Not Taken**，不分配条目。
- **H**：BTB 命中，预测根据历史位：  
  - 历史 T → 预测 **Taken**  
  - 历史 N → 预测 **Not Taken**  
  - 执行后，根据实际结果更新历史位（T 或 N），用于下一次预测。
