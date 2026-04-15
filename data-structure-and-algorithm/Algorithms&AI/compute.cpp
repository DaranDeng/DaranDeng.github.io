#include <iostream>
#include <vector>
int exponential(int c, int n){
    if(n==0) return 1;
    else return c*exponential(c,n-1);
}

int exponential2(int c, int n){
    if(n==0)return 1;
    for (int i = 1; i < n; i++)
    {
    c*=c;
    }
    return c;
}

long long Fibonacci(int n){
    if (n < 0) return 0;

    static std::vector<long long> arr{0, 1};
    //这个数组只初始化一次，之后每次函数再被调用，都会保留上一次已经算出来的内容。
    if (n < static_cast<int>(arr.size())) return arr[n];
    //i = static_cast<int>(arr.size());把size_t强转为int
    //如果当前缓存已经有 arr.size() 个 Fibonacci 值，
    //那么新计算时就从这个下标开始补，避免重复计算前面已经算过的部分
    for (int i = static_cast<int>(arr.size()); i <= n; ++i)
    {
        arr.push_back(arr[i - 1] + arr[i - 2]);
    }
    return arr[n];
}

long long Fibonacci2(int n){
    if(n==0)return 0;
    else if(n==1)return 1;
    return Fibonacci2(n-1)+Fibonacci2(n-2);
}
int main(){
    std::cout<<exponential(3,0)<<std::endl;
    std::cout<<Fibonacci2(0);
    return 0;
}