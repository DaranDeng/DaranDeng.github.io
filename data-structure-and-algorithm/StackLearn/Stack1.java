package StackLearn;

public class Stack1 {
    public static void main(String[] args) {
        ArrayStack a=new ArrayStack();
        a.push("to");
        a.push("be");
        a.push("or");
        a.push("not");
        a.list();
        a.pop();
        a.list();
    }
}
class ArrayStack{
    private String[]s;
    private int n=0;

    public ArrayStack() {
        s=new String[1];
    }


    public void resize(int capacity){
        String[]copy=new String[capacity];
        for (int i = 0; i < n; i++) {
            copy[i]=s[i];
        }
        s=copy;
    }

    public void push(String item){
        if(n==s.length)resize(2*s.length);
        s[n++]=item;
    }
    public String pop(){
        if(n==0)throw new IllegalStateException("Stack underflow");
        String item=s[--n];
        s[n]=null;
        if(n>0&&n==s.length/4) resize(s.length/2);
        return item;
    }
    public void list(){
        if(n==0) {
            System.out.println("栈空");
            return;
        }
        for (int i = n-1; i>=0  ; i--) {
            System.out.printf("the stack[%d]=%s\n",i,s[i]);
        }
    }
}