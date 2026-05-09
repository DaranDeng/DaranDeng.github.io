#include<iostream>
#include<vector>
using namespace std;

int maxSubArray(vector<int>& nums) {
    int sum = 0, m = INT_MIN;
    for (int i = 0; i < nums.size(); i++)
    {
        sum+=nums[i];
        m = max(m,sum);
        if (sum<0)
        {
            sum = 0;
        }
        
    }
    return m;
}

    bool canJump(vector<int>& nums) {
        int bound = 0, len = nums.size(), m=0;
        if(len == 1)return true;
        for (int i = 0; i < len-1; i++)
        {
            
            bound = nums[i]+i;
            m = max(m,bound);
            if (m >= len-1)
            {
                return true;
            }       
        }
        return false;
    }
int main(){
    std::vector<int> nums ={3,2,1,0,4};
    cout<<canJump(nums)<<endl;
}