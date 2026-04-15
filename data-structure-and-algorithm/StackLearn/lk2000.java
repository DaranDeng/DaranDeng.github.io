package StackLearn;

import java.util.Stack;

public class lk2000 {
    public String reversePrefix(String word, char ch) {
        char[] arr=word.toCharArray();
        Stack<Character> s=new Stack<>();
        char []a=new char[arr.length];
        int end=-1;
        for (int i = 0; i < word.length(); i++) {
            s.push(arr[i]);
            if(arr[i]==ch) {
               end=i;
                break;
        }
    }
        if (end==-1)return word;
        for (int i = 0; i < end; i++) {
            a[i]=s.pop();
        }
        for (int i = end+1; i <arr.length ; i++) {
            a[i]=arr[i];
        }
    return new String(a);
}
}
