package StackLearn;

import java.util.Deque;
import java.util.LinkedList;

public class lk739 {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null) return null;
        Deque<Integer> s = new LinkedList<>();
        int []ans=new int[temperatures.length];
        for (int i = 0; i <temperatures.length; i++) {
            int n=temperatures[i];
            while (!s.isEmpty() && temperatures[s.peek()] <n){
                int top=s.peek();
                ans[top]=i-s.pop();
            }
            s.push(i);
        }
        return ans;
    }
}
