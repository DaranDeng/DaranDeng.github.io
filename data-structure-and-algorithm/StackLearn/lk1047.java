package StackLearn;

import java.util.Deque;
import java.util.Stack;

public class lk1047 {
    public String removeDuplicates(String s) {
        Stack <Character>stack=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(stack.isEmpty()||c!=stack.peek())
                stack.push(c);
            else stack.pop();
        }
        String str="";
        while (!stack.isEmpty()){
            str=stack.pop()+str;
        }
        return str;
    }

    public static void main(String[] args) {
        lk1047 l=new lk1047();
        System.out.println(l.removeDuplicates("abbaca"));
    }
}
