struct Node
{
    int data;
    Node *next;
};

struct DEQueue
{
    Node *head;
    Node *tail;
};

DEQueue *init()
{
    DEQueue *q = new DEQueue();
    /*q->head->next = nullptr;
    q->head.data = 0;
    q->tail = nullptr;*/
    // 你补：设置head和tail
    q->head = nullptr;
    q->tail = nullptr;
    return q;
}

void addHead(DEQueue *q, int val)
{
    Node *n = new Node{val, nullptr};
    q->head = n;
    if (!q->tail)
        q->tail = n;
}

int removeHead(DEQueue *q)
{
    if (!q->head)
        return -1;
    Node *t = q->head;
    int val = q->head->data;
    q->head = q->head->next;
    if (!q->head)
        q->tail = nullptr;
    delete t;
    return val;
}
