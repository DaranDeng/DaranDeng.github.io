#include <iostream>
#include <string>
using namespace std;
string star(int n, string symbol)
{
    string ans = "";
    for (int i = 0; i < n; i++)
        ans.append(symbol);
    return ans;
}
int main()
{
    cout << star(4, "*") << endl;
}