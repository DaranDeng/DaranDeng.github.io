package BinaryTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 1为啥递归终止条件是这个而非nums=null||nums.length==1
 * 2初始化max时为什么不max=0，可能数组有负数吗
 * 3最后分割数组时万一分割后数组长度为0呢，会报错吗
 * 4nums.length==0||nums==null不是等价的吗*/
public class lk654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length==0||nums==null)return null;
        int max=Integer.MIN_VALUE;
        int index=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>max){
                max=nums[i];
                index=i;
            }
        }
        TreeNode root=new TreeNode(max);
        int[] firstPart = Arrays.copyOfRange(nums, 0, index);
        int[] secondPart=Arrays.copyOfRange(nums,index+1,nums.length);
        root.left=constructMaximumBinaryTree(firstPart);
        root.right=constructMaximumBinaryTree(secondPart);
        return root;
    }
    /**
     * 1 nums.length==1时，刚切割完若检测到数组长度为一就返回了，不会进行设置根节点等操作
     * 2 对啊
     * 3 不会，因为nums.length==0时直接返回
     * 4 nums.length == 0：表示当前子数组已经为空（比如在分割后，左/右子数组
     * 没有剩余元素），此时不需要再递归，返回 null。
     * nums == null：表示输入的数组本身是 null，无法处理*/
}
