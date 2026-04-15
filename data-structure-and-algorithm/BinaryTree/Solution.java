package BinaryTree;

public class Solution {
    public boolean isBalanced(TreeNode root){
            return Height(root)!=-1;
    }
    public int Height(TreeNode root){
        if(root==null)return 0;
        int leftHeight=Height(root.left);
        if (leftHeight==-1) return -1;
        int rightHeight=Height(root.right);
        if (rightHeight==-1) return -1;
        if (Math.abs(leftHeight-rightHeight)>1)return -1;
        return (1+Math.max(leftHeight,rightHeight));
    }
}
