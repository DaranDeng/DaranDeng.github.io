#include<cstdio>
void increment(int &x){//传递了"x的引用"作为参数
    x++;
}
void increment_wrong(int x){//传递了"x的数值"作为参数, x的数值被复制了一次
    x++;
}
void increment_pointer(int *p){//传递了"指向某个变量地址的指针"作为参数
    *p++;
}
int main(){
    int a=0;
    increment(a);
    printf("%d\n",a);
    increment_wrong(a);
    printf("%d\n",a);
    increment_pointer(&a);
    printf("%d\n",a);
}