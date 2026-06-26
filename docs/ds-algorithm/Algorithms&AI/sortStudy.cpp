#include <iostream>
#include <vector>
using namespace std;

bool isSorted(vector<int> const vec)
{
    int len = vec.size();
    for (int i = 0; i < len - 1; i++)
    {
        if (vec[i] > vec[i + 1])
        {
            return false;
        }
    }
    return true;
}
/*
Find the smallest number in the unsorted part;
Place it at the correct position in the sorted part;
循环不变量是什么？
*/
void selectionSort(vector<int> &vec)
{
    int len = vec.size();
    if (isSorted(vec))
        return;
    for (int i = 0; i < len-1; i++)
    {
        int index = i;
        // 可以添加一个检查，如果本身就排好顺序无需再排
        for (int j = i + 1; j < len; j++)
        {
            if (vec[j] < vec[index])
            {
                index = j;
                // vec[index] = vec[j];
                // index专门用来记录最小的数的下标，先不急着交换
            }
        }
        // 交换的是值而不是下标
        int temp = vec[i];
        vec[i] = vec[index];
        vec[index] = temp;
    }
}

void selectionSort02(vector<int> &vec)
{
    int i, j, index, temp;
    for (i = 0; i < vec.size()-1; i++)
    {
        for (j = i + 1, index = i; j < vec.size(); j++)
        {
            if (vec[j] < vec[index])
                index = j;
        }
        if (index != i)
        {
            temp = vec[i];
            vec[i] = vec[index];
            vec[index] = temp;
        }
    }
}
// 插入排序：将无序区的第一个元素插入到有序区，如果还比最后一个有序区的元素大，那就不必移动。
// 如果小，那就用顺序查找或者二分查找插入到有序区；
// 最后，更新有序区即扩充一个
void insertionSort01(vector<int> &vec){
    if (isSorted(vec))
        return;
    int len = vec.size();
    for (int i = 1; i < len; i++){
        int j = i;
        while (j>0&&vec[j] < vec[j-1])
        {
            int temp = vec[j];
            vec[j] = vec[j - 1];
            vec[j - 1] = temp;
            j--;
        }
    }
}
void insertionSort02(vector<int> &vec)
{
    if (isSorted(vec))
        return;
    int len = vec.size();
    for (int i = 1; i < len; i++)
    {
        for (int j = i - 1; j >= 0; j--)
        {

            if (vec[j] <= vec[j + 1])
                break;
            int temp = vec[j];
            vec[j] = vec[j + 1];
            vec[j + 1] = temp;
        }
    }
}

// 冒泡排序：比较相邻的元素并交换它们的位置来实现排序。该算法的名称来源于较小的元素会像"气泡"一样逐渐"浮"到列表的最前端。
void BubbleSort(int *a, int n){
    for (int i = 1; i < n; i++){
        bool swapped = false;
        for (int j = 0;j<n-i;j++){
            if (a[j] > a[j+1]){
                swap(a[j], a[j+1]);
                swapped = true;
            }
        }
        // 说明后面的元素始终>=前一个元素，有序
        if (swapped == false)
        {
            break;
        }
        
    }
}

// 归并排序，归并排序最核心的部分是合并（merge）过程：将两个有序的数组 a[i] 和 b[j] 合并为一个有序数组 c[k]．

// 从左往右枚举 a[i] 和 b[j]，找出最小的值并放入数组 c[k]；重复上述过程直到 a[i] 和 b[j] 有一个为空时，将另一个数组剩下的元素放入 c[k]．

// 为保证排序的稳定性，前段首元素小于或等于后段首元素时（a[i] <= b[j]）而非小于时（a[i] < b[j]）就要作为最小值放入 c[k]．
/*作用就是把两个有序的子数组拼起来*/
void merge02(vector<int>&a, vector<int>&b){
    vector<int>c(max(a.size(),b.size()));
    int i = 0, j = 0, k=0;
    while (i<a.size()&&j<b.size())
    {
        if (a[i]<=b[j])
        {
            c[k] = a[i];
            i++;
        }else{
            c[k]=b[j];
            j++;
        }
        k++;
    }
    // 此时一个数组已空，另一个数组非空，将非空的数组并入 c 中
    for(;i<a.size();i++,k++)c[k]=a[i];
    for(;j<b.size();j++,k++)c[k]=b[j];
}

void merge(vector<int>&a, int low, int high,int mid){
    int i=low,j=mid+1,k=0;//k是相对索引
    vector<int>c(high - low+1);
    while (i<=mid&&j<=high)
    {
        if(a[i]<=a[j]){
            c[k]=a[i];
            i++;
        }else{
            c[k]=a[j];
            j++;
        }
        k++;
    }
    if(j>high){
        for(;i<mid;i++,k++)c[k]=a[i];
    }else for(;j<high;j++,k++)c[k]=a[j];
    for (k = low; k <= high; ++k) a[k] = c[k];
}

void fullMergeSort(vector<int>&a,int low,int high){
    if (low<high)
    {
        int mid = low+((high - low)>>1);
        fullMergeSort(a,low,mid);
        fullMergeSort(a,mid+1,high);
        //merge(a,0,l,mid);
        merge(a, low, high, mid); 
    }
}


int partition(vector<int> &a, int left, int right){
    int pivot = a[left];//人为写死的：总是选子数组的第一个元素。
    int i = left + 1, j = right;
    while (i < j)    
    {
        while (a[i]<pivot&&i<j)i++;
        while (a[j]>=pivot&&i<=j)j--;
        if (i < j)
        {
            int t=a[i];
            a[i]=a[j];
            a[j]=t;
        }
    }
    if(left!=j)swap(a[left],a[j]);//处理的是只有两个元素的情况     
    return j;
}
void quickSort(vector<int> &a, int start, int end){
    if (start>=end)return;
    int p = partition(a,start,end);
    quickSort(a,start,p-1);
    quickSort(a,p+1,end);
}
/*https://chat.deepseek.com/share/v9pwygsapwo5bhjx9p，教材的例子是在partition函数里面决定pivot的选择。但是更好的做法是在quicksort函数决定，如下*/
int partition(vector<int> &a, int left, int right,int pivotIdx){
    int i = left + 1, j = right;
    int val = a[pivotIdx];
    while (i < j)    
    {
        while (a[i]<val&&i<j)i++;
        while (a[j]>=val&&i<=j)j--;
        if (i < j)
        {
            swap(a[i],a[j]);
        }
    }
    if(pivotIdx!=j)swap(a[pivotIdx],a[j]);
    //swap(val,a[j]);//处理的是只有两个元素的情况,这行代码想交换 pivot 和 j 位置，但 val 是局部变量，swap(val, a[j]) 只是交换了局部变量和数组元素的值，原先a[pivotIdx]没变，pivot 也没放到正确位置。swap不能乱用啊。     
    return j;
}

void quickSort(vector<int> &a, int start, int end){
    if (start>=end)return;
    // quickSort 选 pivot 的位置
    int pivotIdx = start + (end - start) / 2;  // 选中间,降低复杂度。
    int p = partition(a,start,end,pivotIdx);
    quickSort(a,start,p-1);
    quickSort(a,p+1,end);
}
/*
void Partition(int a[],int low,int high){
	pivot = a[high];
	i = low - 1; //a[low,i] 小元素区域  a[i+1,high-1] 都大于pivot
	for(int j = low; j < high; j++){
		if(a[j] <= pivot){
			i++;
			swap(a[i],a[j]);
		} 
	}
	swap(a[i+1],a[high]); //pivot 放在i+1的位置
	return i+1;
}
void quicksort(int a[],int low,int high){
	if(low < high){
		int p = Partition(a,low,high);
		quicksort(a,low,p-1);
		quicksort(a,p+1,high);
	}
}
*/


int main()
{
    vector<int> vec = {1, 3, 4, 2, 5};
    // insertionSort01(vec);
    // for (int i = 0; i < vec.size(); i++)
    // {
    //     cout << vec[i] << endl;
    // }
    vector<int> v = {6,5,4,3,2,1};
    fullMergeSort(v,0,5);
    for (int i = 0; i < v.size(); i++)
    {
        cout << v[i] << endl;
    }
    getchar();
    return 0;
}