#include <stdio.h>
#include <stdlib.h>
#include <limits.h> // 为了使用 INT_MAX

#define maxsize 10
/*几个基本的认识
1 每个节点都存储了入边的权重，以及出边的指针
2 邻接表的顶点之后连接的都是其邻接节点
3 每个节点都有自己的下标，也有顶点数据，这二者不一样。
下标是给计算机看的（为了快），顶点数据是给人看的（为了语义）。把它们分开，
是为了让你的代码在面对现实需求变化时，不需要把整个图算法推倒重写。*/
// --- 结构体定义 (保持你的原样，稍作类型修正) ---
typedef char Nodetype;

typedef struct Node {
    int adjver;        // 邻接点下标，节点的编号
    double weight;     // 权重
    struct Node *next; // 链表指针
} Node;

typedef struct {
    Nodetype data;      // 顶点数据 (如 'A', 'B')
    Node *firstedge;    // 边表头指针
} vertexNode;

typedef struct {
    vertexNode adjlist[maxsize];
    int numv, nume;
} GraphAdjlist;

// Dijkstra 结果表
typedef struct {
    int visited;
    double dist; // 修正：距离也应该是 double
    int pre;
} dijkstraEntry;

// --- 辅助工具：简单的整型队列 (用于 BFS) ---
typedef struct {
    int data[maxsize];
    int front;
    int rear;
} Queue;

void initQueue(Queue *q) {
    q->front = q->rear = 0;
}

int isEmpty(Queue *q) {
    return q->front == q->rear;
}

void enqueue(Queue *q, int v) {
    if ((q->rear + 1) % maxsize == q->front) return; // 队满
    q->data[q->rear] = v;
    q->rear = (q->rear + 1) % maxsize;
}

int dequeue(Queue *q) {
    if (isEmpty(q)) return -1;
    int v = q->data[q->front];
    q->front = (q->front + 1) % maxsize;
    return v;
}

// --- 图的基本操作 ---

GraphAdjlist* createGraph(int v_count) {
    GraphAdjlist *g = (GraphAdjlist*)malloc(sizeof(GraphAdjlist));
    g->numv = v_count;
    g->nume = 0;
    for (int i = 0; i < maxsize; i++) {
        g->adjlist[i].firstedge = NULL;
        g->adjlist[i].data = 'A' + i; // 自动给点起名为 A, B, C...
    }
    return g;
}

// 修正后的加边函数 (去掉了那个错误的 for 循环)
void addEdge(GraphAdjlist *g, int v1, int v2, double edge) {
    if (v1 < 0 || v1 >= g->numv || v2 < 0 || v2 >= g->numv) {
        printf("越界\n");
        return;
    }
    
    Node *newEdge = (Node*)malloc(sizeof(Node));
    newEdge->adjver = v2;
    newEdge->weight = edge;
    
    // 头插法：新来的插在最前面 (注意：这会导致邻居顺序和插入顺序相反)
    newEdge->next = g->adjlist[v1].firstedge;
    g->adjlist[v1].firstedge = newEdge;
    g->nume++;
}

// --- 1. 深度优先搜索 (DFS) - 递归版 ---
// 核心思想：不撞南墙不回头
int visited_dfs[maxsize] = {0}; // 全局访问标记

void DFS(GraphAdjlist *g, int i) {
    visited_dfs[i]=1;
    Node* p=g->adjlist[i].firstedge;
    while(p!=NULL){
    if(!visited_dfs[i]){
        dfs(g,p->adjver);
    }
}
}

void DFSTraverse(GraphAdjlist *g) {
    printf("DFS Result: ");
    for (int i = 0; i < g->numv; i++) visited_dfs[i] = 0; // 重置
    for (int i = 0; i < g->numv; i++) {
        if (!visited_dfs[i]) DFS(g, i); // 防止有非连通图
    }
    printf("\n");
}

// --- 2. 广度优先搜索 (BFS) - 队列版 ---
// 核心思想：层层递进，像水波纹
void BFS(GraphAdjlist *g, int start) {
    int visited_bfs[maxsize] = {0};
    Queue q;
    initQueue(&q);

    printf("BFS Result: ");
    
    visited_bfs[start] = 1;
    printf("%c ", g->adjlist[start].data);
    enqueue(&q, start);

    while (!isEmpty(&q)) {
        int v = dequeue(&q);
        Node *p = g->adjlist[v].firstedge;
        while (p != NULL) {
            if (!visited_bfs[p->adjver]) {
                printf("%c ", g->adjlist[p->adjver].data);
                visited_bfs[p->adjver] = 1;
                enqueue(&q, p->adjver);
            }
            p = p->next;
        }
    }
    printf("\n");
}
/*先确定起点a，然后以a为起点，找到与a直接相连的节点，计算距离并比较
确定第一次的最短路径a b。
再进行第二次，以b为起点，找到...以此类推*/
// --- 3. Dijkstra 算法 (修正为单源最短路径) ---
void dijkstra(GraphAdjlist *g, int start) {
    // 1. 初始化表
    dijkstraEntry *table = (dijkstraEntry*)malloc(g->numv * sizeof(dijkstraEntry));
    for (int i = 0; i < g->numv; i++) {
        table[i].visited = 0;
        table[i].dist = INT_MAX; //GraphAdjlist *g 无穷大
        table[i].pre = -1;
    }
    table[start].dist = 0; // 起点到自己距离为0

    // 2. 主循环
    for (int count = 0; count < g->numv; count++) {
        // 2.1 贪心策略：找一个没访问过且距离最小的点
        double minDist = INT_MAX;
        int u = -1;
        for(int i=0;i<g->numv;i++){
            if(table[i].dist<minDist&&table[i].visited != 0){
                minDist=table[i].dist;
                u=i;
            }
        }
        if (u == -1) break; // 剩下的点都不可达，结束

        // 2.2 标记访问
        table[u].visited = 1;

        // 2.3 松弛操作 (Relaxation)：看看能不能借道 u 到达邻居 v 会更近
        Node *p = g->adjlist[u].firstedge;//真正涉及到邻接关系的是这里,
        //也就是说第一次的u一定为start，然后更新start的邻居节点的dist
        while (p != NULL) {
            int v = p->adjver;
            if (!table[v].visited && table[u].dist + p->weight < table[v].dist) {
                table[v].dist = table[u].dist + p->weight; // 更新距离
                table[v].pre = u;                          // 更新前驱
            }
            p = p->next;
        }
    }

    // 3. 打印结果
    printf("\nDijkstra from %c:\n", g->adjlist[start].data);
    printf("Dest\tDist\tPath\n");
    for (int i = 0; i < g->numv; i++) {
        printf("%c\t%.1f\t", g->adjlist[i].data, table[i].dist);
        // 简单的回溯打印路径
        int curr = i;
        printf("%c", g->adjlist[curr].data);
        while(table[curr].pre != -1) {
            curr = table[curr].pre;
            printf("<-%c", g->adjlist[curr].data);
        }
        printf("\n");
    }
    free(table);
}

int main() {
    // 创建一个有5个顶点的图 (0~4)
    GraphAdjlist *g = createGraph(5);

    // 构建一个简单的图
    // A(0) -> B(1), A -> C(2)
    addEdge(g, 0, 1, 10);
    addEdge(g, 0, 2, 5);
    // B(1) -> C(2), B -> D(3)
    addEdge(g, 1, 2, 2); 
    addEdge(g, 1, 3, 1);
    // C(2) -> B(1), C -> D(3), C -> E(4)
    addEdge(g, 2, 1, 3);
    addEdge(g, 2, 3, 9);
    addEdge(g, 2, 4, 2);
    // D(3) -> E(4)
    addEdge(g, 3, 4, 4);

    DFSTraverse(g);
    BFS(g, 0); // 从 A 开始
    dijkstra(g, 0); // 从 A 开始

    return 0;
}