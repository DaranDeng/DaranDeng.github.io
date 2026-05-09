#include<stdio.h>
#include<stdlib.h>
typedef struct 
{
     int vertexNum;
     int** adjMatrix;
    /* data */
}Graph;
Graph* initGraph(int num){
    Graph *pgraph=malloc(sizeof(Graph));
    pgraph->vertexNum=num;
    pgraph->adjMatrix=malloc(num*sizeof(int*));
    for (int i = 0; i < num; i++)
    {
        pgraph->adjMatrix[i]=malloc(sizeof(int)*num);
    }
    return pgraph;
} 
void freeGraph(Graph *g){
    for (int i = 0; i < g->vertexNum; i++)
    {
        free(g->adjMatrix[i]);
    }
    free(g->adjMatrix);
    free(g);
}
/*有向有权图*/
void addEdgewithWeight(Graph *g){
    printf("please enter weights \n");
    for (int i = 0; i < g->vertexNum; i++)
    {
        printf("Row %d: ", i);
         for (int j = 0; j < g->vertexNum; j++)
    {
        if(i==j)g->adjMatrix[i][j]=0;
        else scanf("%d",&g->adjMatrix[i][j]);
    }
    // 清空输入缓冲区，防止换行符影响下一行
    while (getchar() != '\n');
}
}


void addEdge(Graph *g){
    printf("please enter 1 or 0 \n");
    for (int i = 0; i < g->vertexNum; i++)
    {
        printf("Row %d: ", i);
         for (int j = 0; j < g->vertexNum; j++)
    {
        if(i==j)g->adjMatrix[i][j]=0;
        else scanf("%d",&g->adjMatrix[i][j]);
        if(g->adjMatrix[i][j]>1)g->adjMatrix[i][j]=1;
    }
    // 清空输入缓冲区，防止换行符影响下一行
    while (getchar() != '\n');
}
}

void Dijkstra(Graph *g){
    int dist[g->vertexNum][g->vertexNum],visited[g->vertexNum][g->vertexNum];

    for (int i = 0; i < g->vertexNum; i++)
    {
         for (int j = 0; j < g->vertexNum; j++)
    {
        
        g->adjMatrix;
    }
}
}
    
void printGraph(Graph *g){
    for (int i = 0; i < g->vertexNum; i++)
    {
        
         for (int j = 0; j < g->vertexNum; j++)
    {
        printf("%d",g->adjMatrix[i][j]);
    }
    printf("\n");
}
}

void isConnect(Graph *g){
    int n,m;
    printf("please enter 2 vertices you want to search,the connection starts from the first to the second \n");
    while(scanf("%d %d",&n,&m)!=EOF){
    printf ("%d\n",g->adjMatrix[n-1][m-1]);
    }
}
int main(){
    int n;
    printf("please enter the number of vertices \n");
    scanf("%d",&n);
    Graph *g=initGraph(n);
    addEdge(g);
    printGraph(g);
    isConnect(g);
    freeGraph(g);
    return 0;
}
