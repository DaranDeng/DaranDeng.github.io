#include<stdio.h>
typedef struct Node;
typedef struct {
    int val;
    Node *next;
}Node;
typedef struct{
    Node *front;
    Node *rear;
}DEqueue;
