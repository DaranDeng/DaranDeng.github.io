/*You should follow the steps below to create a functioning program:  
1.	Create a class ‘Time’. This class should have three private member variables (‘hour’, ‘minute’ and ‘second’).  
2.	Write a constructor for this class which takes three parameters and sets each of hour, minute, and second to the value given, or, to a default of zero if no values are provided:  Time(int h=0, int m=0, int s=0)  
3.	Write a member function, ‘setTime’, which will allow the hour, minute, and second to be changed for an existing object:  
void setTime(int h, int m, int s)  
4.	Overload the output operator ‘<<’ to output the time (e.g., “15:54:32”). Think about what happens here if any of the values are less than 10 (“12:00:01” is nicer output than “12:0:1”).  
NOTE: you will find various tutorials online that give examples of operator overloading. For example, here: https://www.tutorialspoint.com/cplusplus/cpp_overloading.htm. Or here: https://www.programiz.com/cpp-programming/operator-overloading.  
5.	Overload the addition operator ‘+’ to add two Times together. You should make sure that this takes account of going past 60 seconds, 60 minutes, and 24 hours. For example, adding 15:59:59 and 12:00:01, should give 04:00:00.  
6.	Overload the increment operator ‘++’ to add one second to the time when used. Remember that you will need to do this for both the prefix (++t) and postfix (t++) versions:  
Time& operator++()  
Time operator++(int )  
*/
#include<iostream>
using namespace std;
class Time{
    private:
        int hour;
        int minute;
        int second;
    public:
        Time(int h=0, int m=0, int s=0):hour(h),minute(m),second(s){};
        void setTime(int h, int m, int s){
            hour=h;
            minute=m;
            second=s;
        }
        
        Time& operator++(){
            ++second;
            if (second>=60)
            {
                second-=60;
                minute++;
            }
            if (minute>=60)
            {
                minute-=60;
                hour++;
            }
            
            return *this;
        }  
        Time operator++(int ){
            Time temp=*this;
            ++second;
            if (second>=60)
            {
                second-=60;
                minute++;
            }
            if (minute>=60)
            {
                minute-=60;
                hour++;
            }
            return temp;
        }
        friend std::ostream& operator<<(ostream& os,const Time& t);

    };
    std::ostream& operator<<(ostream& os,const Time& t){
        if (t.hour<10)os<<"0";
        os << t.hour << ":";
        if (t.minute<10)os<<"0";
        os << t.minute << ":";
        if (t.second<10)os<<"0";
        os << t.second <<endl;
        return os;
    }
    int main() {
        Time t1(9, 5, 8);    // 09:05:08
        Time t2(15, 0, 1);   // 15:00:01
        
        // 链式操作能够工作，就是因为返回了引用
        std::cout << "Time 1: " << t1 << "\nTime 2: " << t2 << std::endl;
        
        return 0;
}