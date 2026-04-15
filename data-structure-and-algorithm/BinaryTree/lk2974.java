package BinaryTree;

import java.util.Stack;

public class lk2974 {
    public void sort(int []nums){
        int temp;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if(nums[i]>nums[j]){
                    temp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                }
            }
        }
    }
    public int[] playGame(int []nums){
        sort(nums);
        int []arr=new int[nums.length];
        Stack<Integer>stack=new Stack<>();
        for (int i = 0, j = 0; i < nums.length; i += 2, j += 2) {
            // Bob的元素在前（nums[i+1]）
            // Alice的元素在后（nums[i]）
            if (i + 1 < nums.length) { // 确保不越界
                arr[j] = nums[i + 1];
                arr[j + 1] = nums[i];
            }
        }

        return arr;
    }
}
