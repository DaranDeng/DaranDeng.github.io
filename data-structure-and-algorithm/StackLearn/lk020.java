package StackLearn;

import java.util.Stack;

public class lk020 {
    public static boolean pipei(String []str){
        Stack<String>stack=new Stack<>();
        if (str.length%2!=0)return false;
        for (String s : str) {
            if (s.equals("{") || s.equals("[") || s.equals("(")) {
                stack.push(s);
            } else{
                if (stack.isEmpty())
                    return false;
            }

                String top=stack.peek();
                /**if (!top.equals("{")&&s.equals("}")) return false;
                *if (!top.equals("[")&&s.equals("]")) return false;
                *if (!top.equals("{(")&&s.equals(")")) return false;
                *stack.pop();*/
            if (s.equals("}") && top.equals("{")) {
                stack.pop();
            } else if (s.equals("]") && top.equals("[")) {
                stack.pop();
            } else if (s.equals(")") && top.equals("(")) {
                stack.pop();
            } else {
                return false; // 不匹配的情况
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[]s={"{","{","}","}"};
        System.out.println(pipei(s));
    }
}
