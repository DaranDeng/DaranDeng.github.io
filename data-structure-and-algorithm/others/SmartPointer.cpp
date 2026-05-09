#include<iostream>
#include<vector>
#include<memory>
#include<string>
using namespace std;
class Person{
    public:
        string name;
        Person(const string &name){
            this->name=name;
            cout << name << " has been created.\n";
        }
        ~Person(){
            cout << name << " has been destroyed.\n";
        }

};

class Book {
public:
    string title;
    Book(string title) : title(title) {}
};

class A;

class B {
public:
    weak_ptr<A> a;
    B() { cout << "B created.\n"; }
    ~B() { cout << "B destroyed.\n"; }
};

class A {
public:
    shared_ptr<B> b;
    A() { cout << "A created.\n"; }
    ~A() { cout << "A destroyed.\n"; }
};


int main(){
    /*shared_ptr<Person> p1 =make_shared<Person>("John");
    shared_ptr<Person>p2=p1;
    cout << "p1 is sharing ownership with p2." << endl;

    while (p1.use_count()>0)
    {
        p1.reset();
        cout<<"the use_count: " <<p1.use_count()<<endl;
        cout<<"the use_count: " <<p2.use_count()<<endl;
    }
    */
    /*unique_ptr<Book>pbook=make_unique<Book>("C++ primer");
    cout<<"Book title: " << pbook->title << endl;*/
    shared_ptr<A> a = make_shared<A>();
    /*
    开辟内存：make_shared<A>() 在堆上给 A 对象分配了一块内存。

构造对象：A() 构造函数被调用，所以会打印 "A created."。

创建智能指针：shared_ptr<A> a 指向这块堆内存，同时 shared_ptr 内部
会维护一个 引用计数，此时 a 的计数器是 1。
此时a指向的A对象的成员变量指针shared_ptr<B> b还未指向任何值，还没赋值*/
    shared_ptr<B> b = make_shared<B>();
    
    a->b = b;
    b->a = a;  // 循环引用问题
/*a 是 shared_ptr<A>，引用计数 = 1

b 是 shared_ptr<B>，引用计数 = 1

a->b 是 shared_ptr<B>，赋值后，B 的引用计数 +1 → 2

b->a 是 weak_ptr<A>，赋值后 不增加 A 的引用计数，这是关键！*/
    
    getchar();
    return 0;
}