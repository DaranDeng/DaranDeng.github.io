package BinaryTree;

public class Solution530 {
    private int mindiff=Integer.MAX_VALUE;
    TreeNode pre=null;
    public void getMin(TreeNode node){
        if(node==null)return ;
        getMin(node.left);
        if(pre!=null) mindiff=Math.min(mindiff,Math.abs(pre.val-node.val));
        pre=node;
        getMin(node.right);
    }
    public int gg(TreeNode root){
        getMin(root);
        return mindiff;
    }
}

