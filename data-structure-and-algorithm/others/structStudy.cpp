#include <iostream>
#include <vector>
#include <memory>
#include <string>
using namespace std;
typedef struct
{
    string name;
    int age;
} stu;
typedef struct
{
    string name;
    stu app;
} teacher;
int main()
{
    teacher t;
    t.name = "cy";
    t.app.name = "ddr";
    t.app.age = 19;
    cout << t.name << endl
         << t.app.age;
    getchar();
    return 0;
}