/*namespace Math {
    const double PI = 3.14159;
    double circleArea(double radius) {
        return PI * radius * radius;
    }
}

namespace Geometry {
    const double PI = 3.14;  // 不同的PI值
    
    double rectangleArea(double length, double width) {
        return length * width;
    }
}

namespace Utils
{
    void print(){
        printf("%llf\n",Math::PI*Geometry::PI);
    }
} // namespace Utils

int main(){
    cout<<Math::PI<<endl;
    cout<<Geometry::PI<<endl;
    cout<<Math::circleArea(5)<<endl;
    cout<<Geometry::rectangleArea(4,3)<<endl;
    Utils::print();
    getchar();
    return 0;
};*/

#include <iostream>
using namespace std;
namespace MySpace {
    int value = 100;
    
    enum Colors {
        RED = 1,
        BLUE = 2,
        GREEN = 3
    };
}

template<class T>
T add(T a, T b) {
    return a + b;
}
template<>
string add <string>(string a,string b){
    return a.append(b);
}
inline int multiply(int x, int y) {
    return x * y;
}

int main() {
    // 下面代码有多处错误，请找出并修复
    cout << MySpace::value << endl;
    
    MySpace::Colors myColor = MySpace::RED;
    cout << "Color: " << myColor << endl;
    
    double result1 = add(5.0, 3.2);
    cout << "Addition: " << result1 << endl;
    
    string result2 = add(string("Hello"), string("World"));
    cout << "String add: " << result2 << endl;
    
    int product = multiply(4, 5);
    cout << "Product: " << product << endl;
    getchar();
    return 0;
}

// 请完成以下任务：
// 1. 在main函数中分别使用两个命名空间中的PI值
// 2. 计算半径为5的圆的面积（使用Math命名空间）
// 3. 计算长为4、宽为3的矩形面积
// 4. 创建一个自定义命名空间Utils，包含一个打印函数
/*#include <iostream>
#include <vector>
using namespace std;

// 任务1：创建通用交换函数模板
template<typename T>
void swapValues(T& a, T& b) {
    // 请实现交换逻辑
    T temp=a;
    a=b;
    b=temp;
    cout<<"a= "<<a<<endl;
    cout<<"b= "<<b<<endl;
}

// 任务3：创建打印数组的函数模板
template<typename T, int size>
// 这里有两个模板参数：T(类型)和size(非类型)
// 请补充函数声明和实现
void print(T(&a)[size]){
    for (int i = 0; i < size; i++)
    {
        cout<<a[i]<<" ";
    }
}
// 任务4：特化版本 - 为char数组创建特殊的打印函数
// 特化时，必须为所有模板参数提供具体值
template<>
void print<string,2>( string (&a)[2]){
        cout << "特化版本 - string数组[2]: ";
    for (int i = 0; i < 2; i++) {
        cout << "\"" << a[i] << "\" ";  // 加引号显示更清楚
    }
    cout<<endl;
}

template<>
void print<char,10>(char (&a)[10]){
    for (int i = 0; i < 10 && i != '0'; i++)
    {
        cout<<a[i];
    }
    cout<<endl;
}
/*实例化与具体化（特化）：一个是以普通函数模板为模板之后的产物，
一个是本身是函数模板，只不过是限定了类型。有点类似于原模版的重载*/
// 请补充特化版本
/*int main(){
    string haha[2]= {"cdsasvvdv","cssvd"};
    int a=10,b=9;
    char ha[10]="cdsasvvdv";
    swapValues(a,b);
    print(haha);
    print(ha);
    getchar();
}*/
