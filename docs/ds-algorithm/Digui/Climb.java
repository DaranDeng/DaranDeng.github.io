package Digui;

import java.util.Scanner;

public class Climb {
    public static int ClimbMethod(int n){
        if(n==1)return 1;
        if(n==2)return 2;
        return ClimbMethod(n-1)+ClimbMethod(n-2);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(ClimbMethod(sc.nextInt()));
    }
}
