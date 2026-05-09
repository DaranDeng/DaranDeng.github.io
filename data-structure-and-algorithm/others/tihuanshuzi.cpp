#include<iostream>
using namespace std;
int main() {
    string s;
    while(cin>>s){
        int old=s.size()-1,count=0;
        for(auto c:s){
            if(c>'0'&&c<'9')count++;
        }
        s.resize(s.size()+count*5);
        int snewIndex=s.size()-1;
        while(old>=0){
            if(s[old]>'0'&&s[old]<'9'){
                s[snewIndex--] = 'r';
                s[snewIndex--] = 'e';
                s[snewIndex--] = 'b';
                s[snewIndex--] = 'm';
                s[snewIndex--] = 'u';
                s[snewIndex--] = 'n';
            }else s[snewIndex--]=s[old];
            old--;
        }
        cout<<s<<endl;
    }
    return 0;
}