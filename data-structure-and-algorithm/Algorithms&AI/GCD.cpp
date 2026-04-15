#include<iostream>

int findGCD(int a,int b){
    if (a < b)
    {
        int temp;
        temp=a;
        a=b;
        b=temp;
    }
    int max=0;
    //if(a%b==0)return b;
    //for (int i = 1; i*i <= b; i++)
    for (int i = b; i >= 1; i--)
    {
        if(a%i==0&&b%i==0)max=i;
    }
    return max;
}

/*既然要从i开始找俩数最大公约数，
那么要把问题拆解成数字i来更新max和从i+1开始找最大公约数*/
int findGCD2(int a,int b,int i,int max){
    if (a < b)
    {
        int temp=a;
         a=b;
        b=temp;
    }

    if (i > b)
    {
        return max;
    }

    if(a%i==0&&b%i==0)
    {
        max=i;
    }

    return findGCD2(a,b,i+1,max);
}

int gcdCorrect(int a, int b) {
    // 基准条件：当 b 为 0 时，a 就是最大公约数
    if (b == 0) {
        return a;
    }
    
    // 递归关系：gcd(a, b) = gcd(b, a % b)
    return gcdCorrect(b, a % b);
}
int main(){
    std::cout<<findGCD(3,7)<<std::endl;
    std::cout<<findGCD2(3,7,1,1)<<std::endl;
}
