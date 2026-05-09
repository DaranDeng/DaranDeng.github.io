#include<stdio.h>
#include<stdlib.h>

#define maxsize 10
typedef struct
{
    int numV,numE;
    int adjMatrix[maxsize][maxsize];
    /* 传值开销大
    空间浪费严重：即使实际顶点数很少，也要分配maxsize×maxsize空间
    访问更快：直接数组访问，无指针解引用开销 */
}Graph;
typedef struct{
    int src;
    int dest;
    int weight;
}Edge;
typedef struct{
    int matrix[maxsize][maxsize];
}mst;
/*
typedef struct {
    int maxsize;    // 最大顶点数
    int numV;       // 当前顶点数
    int numE;       // 当前边数
    int** adjMatrix; // 邻接矩阵
} Graph;*/
//这样的话就可以动态调整adjmatrix的大小，节点数不一定等于maxSize
//传值开销小
//适合节点数目多

Graph* createAdjacencyMatrix(){
    Graph* g=malloc(sizeof(Graph));
    
}
