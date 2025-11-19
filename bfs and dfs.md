  # bfs 和 dfs
  
## 思考
1. 搞清楚要入队或出队的节点是由什么表示，下标？结构体？类型又是什么。
2. 同1，搞清楚dfs的时候标记数组visited的下标是什么含义，递归传递的形参是数组下标还是什么
3. 题目要求的是遍历顺序还是连通分量的数量（只关注连通性），若为后者，两种遍历都可，典型的有统计问题。
4. bfs类似于层序遍历，先访问顶点，再访问其所有邻居；dfs一条路走到底，递归的尽头是联不通或已经访问的节点，此时立即回溯
## 遍历逻辑
+ DFS：访问S，若尚有未被访问的邻居，任选其一u，递归（u）；否则，返回。
  +   伪代码：
 ```plaintext
函数 DFS(图 graph, 起始节点 start):
    创建 visited 集合 // 记录已访问节点
    调用 DFS_递归(graph, start, visited)

函数 DFS_递归(图 graph, 当前节点 current, 集合 visited):
    // 1. 处理当前节点
    标记 current 为已访问 (visited.add(current))
    打印或处理 current 节点数据
    
    // 2. 递归访问邻居
    对于 current 的每个邻居 neighbor:
        如果 neighbor 未被访问:
            调用 DFS_递归(graph, neighbor, visited)
 ```
    
  
+ BFS：节点一旦被访问，未被访问的邻居被加入队列末尾。当前节点从队列前端取出。
  +   伪代码：

  ```plaintext
    函数 BFS(图 graph, 起始节点 start):
    创建队列 queue
    创建 visited 集合
    
    将 start 加入 queue
    标记 start 为已访问
    
    当 queue 不为空:
        current = queue.dequeue() // 出队
        打印或处理 current
        
        对于 current 的每个邻居 neighbor:
            如果 neighbor 未被访问:
                标记 neighbor 为已访问
                将 neighbor 加入 queue
  ```


