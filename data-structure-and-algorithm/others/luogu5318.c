#include<stdio.h>
#include<stdlib.h>
#define maxsize 20
typedef struct Node
{
    int adjver;//下标
    struct Node *next;
    /* data */
}Node;
typedef struct 
{
    int data;
    Node *firstedge;
    /* data */
}vertexNode;
typedef struct 
{
    vertexNode adjlist[maxsize];
    int numv,nume;
    /* data */
}GraphAdjlist;


GraphAdjlist* createGraph(){
    GraphAdjlist *g=malloc(sizeof(GraphAdjlist));
    if (!g) {
        printf("内存分配失败\n");
        return NULL;
    }
    g->nume=0;
    g->numv=0;
    for (int i = 0; i < maxsize; i++)
    {
        g->adjlist[i].firstedge=NULL;
        g->adjlist[i].data=0;
        /* code */
    }
    return g;
}
/*这里的v1,v2是下标*/
void addEdge(GraphAdjlist *g,int v1,int v2){
    if (v1<0||v1>=g->numv||v2<0||v2>=g->numv)
    {
        /* code */
        printf("越界");
        return;
    }
        Node *newEdge=malloc(sizeof(Node));
        /* code */
        if (!newEdge) {
            printf("内存分配失败\n");
            return NULL;
        }
        newEdge->adjver=v2;
        // 头插法：将新边插入到v1的邻接表开头
        newEdge->next = g->adjlist[v1].firstedge;
        g->adjlist[v1].firstedge = newEdge;
        g->nume++;
}
void solve(GraphAdjlist *g,int *used){
    for (int i = 0; i < g->numv; i++)
    {
        if(!used[i]){
            printf("%d",g->adjlist[i].data);
            used[i]=1;
        }
    }
    
}

void freeall(GraphAdjlist *g){
    
    /*for(int i=0;i<maxsize;i++){
    free(g->adjlist[i]);
    }
    free(g);*/
    // 逐结点释放每个邻接表
    if(g==NULL)return;
    for (int i = 0; i < g->numv; ++i) {
        Node *p = g->adjlist[i].firstedge;
        while (p) {
            Node *tmp = p->next;
            free(p);
            p = tmp;
        }
        g->adjlist[i].firstedge = NULL;
    }
    free(g);
}
int main(){
    GraphAdjlist *g=createGraph();
    int n,m;
    scanf("%d %d",&n,&m);
    g->numv=n;
    g->nume=m;
    int used[g->numv];
    used[0]=1;

    for (int i = 1; i <= m; i++)
    {
        int x,y;
        scanf("%d %d",&x,&y);
        addEdge(g,x,y);
        /* code */
    }
    solve(g,used);
    return 0;
}