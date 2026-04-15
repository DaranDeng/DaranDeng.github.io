package StackLearn;

import java.util.Stack;

public class lk32 {
    /**public int longestValidParentheses(String s) {
        Stack<Character> stack=new Stack<Character>();
        char c;
        int n=0;
        for (int i = 0; i < s.length(); i++) {
            c=s.charAt(i);
                if (!stack.isEmpty()&&c==')') {
                    stack.pop();
                }else stack.push(c);
        }
        while (!stack.isEmpty()){
            stack.pop();
            n++;
        }
        return s.length()-n;
    }*/
    //你的方法本质上是统计 连续的匹配括号，而不是所有可能的匹配
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        char c;
        int max=0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            }else{
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }else {
                    max=Math.max(max,i-stack.peek());
                }
            }
        }
        return max;
    }
}
