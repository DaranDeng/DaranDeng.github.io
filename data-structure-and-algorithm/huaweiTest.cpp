/*
BPE算法是迭代地合并最频繁出现的相邻字符对。需要做的如下
1，统计频率。
2，找出最佳对（频率最高的相邻对）；若相同，合并字典序小的。终止条件：最高频率为1或者无相邻对
3，合并：将最佳对合并为一个新token。合并时采取从左到右的贪心。
输入：最大合并次数K，初始字符串。
输出：BPE处理后的字符串。
例子：
输入5和 字符串a a b d a a b a c 
('a','a')最多，第一次合并后得到
aa b d aa b a c
('aa','b')最多，第一次合并后得到
aab d aab a c
终止
*/
#include <iostream>
#include <map>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;
vector<string>bpe(int k, vector<string>tokens){
    /*先找出最高频率的字符对*/
    for (int i = 0; i < k; i++)
    {
        map<string,int>pair = countPairs(tokens);
        string bestPair = findBestPair(pair);
        
        if (pairFreq[bestPair] <= 1) break; // 如果最高频率为1，终止
        for(int j = 0;j<tokens.size();j++){
            if (j<tokens.size()-1&&tokens[i]+""+tokens[i+1]==bestPair)
            {
                
            }
            
        }
    }
    
}