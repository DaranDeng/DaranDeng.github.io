#include<iostream>
#include <string>
using namespace std;

/*1 把n-1移到过渡杆上，
把起始杆的最后一个环移到目标杆，
把过渡杆的n-1移到目标杆
注意：起始杆是由参数在列表中的位置决定，跟变量名没关系*/
void move(int n,char start,char des,char temp){
    if(n==1){
        std::cout<<start<<"->"<<des<<'\n';
        return;
    }
    move(n-1,start,temp,des);
    std::cout<<start<<"->"<<des<<'\n';
    move(n-1,temp,des,start);
}

// ab表示从a移动到b
string hanoi02(int n, char src, char dst, char aux){
    if (n == 0)
    {
        return "";
        /* code */
    }
    string res = "";
    // hanoi02(n-1,src,aux,dst);如果这样返回的结果丢失了啊。
    res+=hanoi02(n-1,src,aux,dst);
    res = res + src;
    res = res + dst;
    res+=hanoi02(n-1,aux,dst,src);
    return res;
}

std::string hanoi(int n, char src, char dst, char aux) {
    if(n == 0) {
        return "";  // 返回空字符串
    }
    
    std::string res = "";
    res += hanoi(n-1, src, aux, dst);  // 关键：接收返回值
    res += src;
    res += dst;
    res += " ";  // 添加分隔符，便于阅读
    res += hanoi(n-1, aux, dst, src);  // 关键：接收返回值
    
    return res;
}

int main(){
    int n=3;
    std::cout<<hanoi(n,'a','c','b')<<std::endl;
    return 0;
}