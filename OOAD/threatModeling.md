## 一、这门课在讲什么？

这门课叫 **Threat Modeling（威胁建模）**，本质上是**在写代码之前，先模拟黑客会怎么攻击你的系统**。

它的核心逻辑就三句话：
1. **系统哪里值钱？**（资产）
2. **黑客怎么搞破坏？**（威胁）
3. **我们怎么防？**（缓解措施）

---

## 二、核心知识点（附英文术语）

### 1. 常见攻击类型（你要认识这些“坏人招式”）

| 攻击类型 | 英文 | 一句话解释 | 真实案例 |
|---------|------|-----------|---------|
| **拒绝服务** | Denial of Service (DoS) | 发海量请求把系统弄瘫 | 攻击者用脚本狂刷你的登录接口，服务器 CPU 爆了 |
| **SQL注入** | SQL Injection | 在输入框里写恶意 SQL，骗数据库吐数据 | 登录框输入 `' OR 1=1 --`，直接绕过密码登录 |
| **跨站脚本** | Cross-site Scripting (XSS) | 在网页里注入恶意 JS 代码，偷用户信息 | 评论区写 `<script>偷cookie</script>`，别人一打开就被偷 |
| **缓冲区溢出** | Buffer Overflow | 给程序塞超长数据，让它崩溃或执行恶意代码 | 老式 C 程序常见，现在 Java/Python 较少 |
| **欺骗** | Spoofing | 假装自己是别人 | 伪造邮件地址发钓鱼邮件，伪造网站骗你登录 |
| **篡改** | Tampering | 改不该改的东西 | 拦截网络请求，把收款账号改成自己的 |
| **抵赖** | Repudiation | 干完坏事不认账 | 系统没日志，黑客删了数据说“不是我干的” |
| **信息泄露** | Information Disclosure | 不该看的人看到了数据 | 错误信息直接把数据库密码打印出来了 |
| **权限提升** | Elevation of Privilege | 普通用户变管理员 | 修改请求参数 `role=user` 改成 `role=admin` |

---

### 2. 威胁建模的核心框架：STRIDE（必背）

**STRIDE** 是微软提出的威胁分类法，每个字母代表一类威胁：

| 字母 | 英文 | 中文 | 举个栗子 |
|------|------|------|---------|
| **S** | Spoofing | 欺骗 | 伪造身份登录系统 |
| **T** | Tampering | 篡改 | 改数据库里的成绩 |
| **R** | Repudiation | 抵赖 | 操作没日志，不承认删了文件 |
| **I** | Information Disclosure | 信息泄露 | 返回报文里带了别人手机号 |
| **D** | Denial of Service | 拒绝服务 | 刷爆接口，让系统卡死 |
| **E** | Elevation of Privilege | 权限提升 | 普通用户搞到管理员权限 |

> ✅ **考试重点**：给你一个场景，让你判断属于 STRIDE 里的哪一类。

---

### 3. 威胁建模的三个主要步骤

#### ① 分解应用（Decompose the Application）
画图，搞清楚：
- **外部实体**（External Entity）：谁在用系统？用户、第三方系统
- **处理过程**（Process）：系统在做什么？登录、支付
- **数据存储**（Data Store）：数据放哪？数据库、文件
- **数据流**（Data Flow）：数据怎么流动？
- **信任边界**（Trust Boundary）：哪里是可信的，哪里是不可信的？

> 工具：**数据流图（Data Flow Diagram, DFD）**

#### ② 确定并排序威胁（Determine and Rank Threats）
用 **STRIDE** 一个个过：
- 这个数据流可能被**欺骗**吗？
- 这个存储会被**篡改**吗？
- 这个接口会被**DoS**吗？

#### ③ 确定缓解措施（Determine Countermeasures and Mitigation）
针对每个威胁，想怎么防：
- 欺骗 → **多因素认证（MFA）**
- 篡改 → **加密 + 数字签名**
- 信息泄露 → **HTTPS + 权限控制**
- DoS → **限流 + 负载均衡**

---

### 4. 数据流图（DFD）基本符号

| 符号 | 名称（英文） | 含义 |
|------|-------------|------|
| 圆角矩形 / 圆形 | Process | 处理过程，比如“验证登录” |
| 箭头 | Data Flow | 数据流动方向 |
| 两条横线 | Data Store | 数据存储，比如数据库 |
| 矩形 | External Entity | 外部实体，比如用户、银行系统 |
| 虚线 | Trust Boundary | 信任边界，比如防火墙内外 |

---

## 三、我给你出几道题，你练练手

### 题1：判断 STRIDE 类型
> 用户 A 在登录框输入 `admin' --`，绕过了密码验证，直接以 admin 身份进入系统。

**问**：这属于 STRIDE 里的哪一类？

---

### 题2：判断 STRIDE 类型
> 攻击者不断向服务器发送超大的文件上传请求，导致服务器磁盘写满，正常用户无法上传文件。

**问**：这属于 STRIDE 里的哪一类？

---

### 题3：威胁识别（开放题）
> 一个在线书店系统，用户可以登录、搜书、下单、支付（调用第三方支付网关）。  
> 支付成功后，系统会把订单信息存到数据库，并发邮件给用户。

**请你用 STRIDE 列出至少 3 个可能的安全威胁**，并简单说明怎么防。

---

你先做，做完我给你点评。  
这样比你一页页看 PPT 效率高 10 倍 💪

## 📘 Threat Modelling 练习题（共 15 题）

### 一、选择题（单选）

**1. What is the primary goal of threat modelling?**  b
A. To write more code faster  
B. To identify and mitigate security vulnerabilities early  
C. To replace penetration testing  
D. To document system architecture  

**2. Which of the following is NOT a common threat modelling benefit?**  c
A. Raise security awareness across the team  
B. Expose problems before deployment  
C. Guarantee 100% security  
D. Reduce embarrassing security failures  

**3. In STRIDE, "T" stands for:**  b
A. Tracking  
B. Tampering  
C. Timing  
D. Token theft  

**4. Which attack involves flooding a system with requests to make it unavailable?**  b
A. SQL injection  
B. Spoofing  
C. Denial of Service (DoS)  
D. Repudiation  

**5. 以下哪项是防止 SQL 注入的有效手段？**  a
A. 使用复杂的密码  
B. 使用参数化查询或预编译语句  
C. 关闭服务器日志  
D. 增加服务器内存  

---

### 二、判断题

**6. Threat modelling should only be done after the system is fully developed.**  对
+ 威胁建模应该在早期分析和设计阶段就开始，而不是等到开发完成之后。PPT 第 5 页明确说："Start during early analysis and design"
**7. 在 DFD 中，外部实体（External Entity）通常位于信任边界之外。**  对
+ 答案：bingo
**8. Repudiation 指的是用户无法否认自己执行过的操作，这需要日志和审计机制来防止。**  错，是抵赖，黑客删除日志，否认是自己做的。
+ Repudiation（抵赖）指的是用户可能否认做过某操作，所以需要日志和审计来防止抵赖。你说的“黑客删除日志”是攻击者破坏证据，也属于 repudiation 的范畴，是对的。
**9. XSS 攻击和 SQL 注入本质上是同一种攻击方式。**  是，因为是输入脚本和恶意代码
+ XSS：向浏览器注入脚本，窃取 cookie/会话。SQL 注入：向数据库注入 SQL 语句，窃取/篡改数据。目标不同、位置不同、防御不同，不是同一种攻击。
**10. 使用 SYN cookies 可以帮助防止 SYN flood 类型的 DoS 攻击。** 对。 
+ SYN cookies 确实是防 SYN flood 的经典手段。
---

### 三、简答题

**11. 请用自己的话解释 STRIDE 中的 "S" 和 "I" 分别代表什么，并各举一个例子。**  Spoofing，？information disclosure，A的银行卡密码被黑客窃取了。
+ 欺骗:攻击者伪造银行网站，让用户输入账户密码。
+ 信息泄露：数据库配置错误，所有用户借阅记录可公开访问
**12. 什么是信任边界（Trust Boundary）？在 DFD 中为什么重要？**  
哪些可信，哪些不可信。

+ 信任边界（Trust Boundary）：系统中不同信任级别区域之间的分界线
+ 为什么重要：数据跨越信任边界时，需要验证、加密、权限检查，否则容易产生漏洞（如从外部传数据到内部时不检查）
**13. 请列出三种你可以用来防止信息泄露（Information Disclosure）的措施。**  
密码
人脸识别
指纹识别。
+ 你列的是身份认证手段，不是防止信息泄露的手段。认证只是第一步，信息泄露发生在传输、存储、访问控制环节。

正确答案示例：

加密传输数据（如 HTTPS）

加密存储敏感数据（如密码加盐哈希）

最小权限原则（用户只能看自己的数据）
---

### 四、场景应用题

**14. 你正在设计一个在线图书馆系统，用户可以通过网页借书。**  
- 用户登录后可以查看借阅记录  
- 系统后台连接数据库  
- 支付功能调用第三方支付网关  

**请根据 STRIDE 模型，为这个系统列出至少 3 种可能的威胁，并说明属于 STRIDE 中的哪一类。**  

+ Spoofing欺骗：伪造用户登录
I信息泄露：泄露借阅记录以及支付密码
E权限提升：变成管理员权限，直接跳过调用第三方支付网关环节。
---

**15. 下面是一个简化的 DFD 片段：**  
```
[User] --(login request)--> [Login Process] --(query)--> [User DB]
```
**请回答：**
- 哪些是外部实体？user and 系统机器
- 哪些是数据存储？数据放在后台数据库
- 这个流程中可能存在哪些威胁？（至少说出 2 个，并说明属于 STRIDE 哪一类）

+ 只有user，因为必须是在图中有显示的。
+ User DB，即user database。
+ sproofing:攻击者冒充合法用户发送登录请求；tampering，篡改发往User DB的查询语句，比如把 WHERE user = 'A' 改成 OR 1=1；Information Disclosure：登录请求在网络中被嗅探，泄露密码。
