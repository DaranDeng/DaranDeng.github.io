#include<stdio.h>
/*int BS(int a[],int x,int n){
    int low,high=n;
    while (low<=high)
    {
        int mid=low+((high-low)>>1);
        if(a[mid]=x){
            return mid;
        }
        else if (a[mid]<x)
        {
           low=mid+1;
        }
        else high=mid-1;
    }
    return -1;
}*/

int BSwithRecursion(int a[],int low,int high,int x){
    if (low>high)
    {
        return -1;
    }
    int mid=low+(high-low)>>1;
    if(a[mid]=x){
            return mid;
        }
    else if(a[mid]<x){
        return BSwithRecursion(a,mid+1,high,x);
    }else return BSwithRecursion(a,low,mid-1,x);
}
/*左闭右开区间*/
int BS2(int a[],int n,int length){
    int l=0,r=length;
    int mid;
    while(l<r){
        mid=l+((r-l)<<1);//+优先级大于位运算
        if(n<a[mid])r=mid;
        else if(n>a[mid])l=mid+1;
        else return mid;
    }
    return -1;
}

int BSwithRecursive2(int a[],int n,int length,int l,int r){
    if(l>=r)return -1;
    int mid=l+((r-l)<<1);
    if(n==a[mid])return mid;
    else if(n<a[mid])BSwithRecursive2(a,n,length,l,mid);
    else BSwithRecursive2(a,n,length,mid+1,r);
}