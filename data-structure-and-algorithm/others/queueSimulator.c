#include<stdio.h>
#include "queue.h"
DEqueue* init(){
    //DEqueue *p=malloc(sizeof(DEqueue *));����Ŀռ䲻��������
    DEqueue *p=malloc(sizeof(DEqueue ));
    //p->front=malloc(sizeof(Node));
    //p->rear=malloc(sizeof(Node));
    //��Ҫ���ϸ�ָ�����ռ������ʱ����ָ�����
    //����ҪΪfront��rear��������ռ䣬����Ӧ�ó�ʼ��ΪNULL����ʾ�ն��С�
    p->front = NULL;  // ֱ����ΪNULL������Ҫmalloc
    p->rear = NULL;
    return p;
}

void addTorear(DEqueue *p,int val){
    Node* n=malloc(sizeof(Node*));//�ĳ�Node���ͻ�����
    n->val=val;
    n->next=NULL;
    if(p->front==NULL){
        p->front=n;
        p->rear=n;
    }else{
        p->rear->next=n;
        p->rear=n;
    }
}
void addTofront(DEqueue *p,int val){
    Node* n=malloc(sizeof(Node));//�ĳ�Node���ͻ�����
    n->next=p->front;
    n->val=val;
    if(p->front==NULL){
        p->front=n;
        p->rear=n;
    }else{

        p->front=n;
    }
}
int removeFront(DEqueue *p){
    if(p->front!=NULL){
        Node *s=p->front;
        int v=s->val;
        p->front=s->next;
        free(s);
        //���ɾ�������Ϊ�գ���Ҫ����rear
        if(p->front == NULL){
            p->rear = NULL;
        }
        return v;
    }else{
        printf("empty ");
        return -1;
    }
}
void empty(DEqueue *p){
    while(p->front!=NULL){
        removeFront(p);
    }
    free(p);
}
int main()
{
    DEqueue *q=init();
    addTofront(q,12);
    addTorear(q,3);
    int n1=removeFront(q);
    int n2=removeFront(q);
    printf("%d%d",n1,n2);
    empty(q);
    return 0;
}
