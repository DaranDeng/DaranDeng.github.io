package StackLearn;

import java.util.Deque;
import java.util.LinkedList;

public class lk239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==1)return nums;
        MyQueue myQueue=new MyQueue();
        int n= nums.length-k;
        int[]max=new int[n+1];
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        max[0]= myQueue.peek();
        for (int i = 0; i < n; i++) {
            myQueue.poll(nums[i]);
            myQueue.add(nums[i+k]);
            max[i+1]= myQueue.peek();
        }
        return max;
    }
    }
class MyQueue{
    Deque<Integer>deque=new LinkedList<>();
    void poll(int val){
        if(!deque.isEmpty()&&val==deque.peek()){
            deque.poll();
        }
    }
    int peek(){
        return deque.peek();
    }
    void add(int val){
        while (!deque.isEmpty()&&deque.getLast()<val){
            deque.removeLast();
        }
        deque.add(val);
    }
}