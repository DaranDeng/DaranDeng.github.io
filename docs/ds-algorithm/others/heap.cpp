#include<iostream>
#include<vector>
#include<math>
using namespace std;

vector<int>arr;
//建立大根堆
void ConstructMaxHeap(){
    for(int i=arr.size()/2;i>=0;i--){
        processElement(i);
    }
}
void processElement(int i){
    int max=max(arr[2*i+1],arr[2*i]);
    if(arr[i]<max)swap(arr[i],max);//交换值而不是交换下标

}