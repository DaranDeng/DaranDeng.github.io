package BinaryTree;

public class Diameter {
    int max=0;
    public int diameterOfBinaryTree(TreeNode root){
        longestPath(root);
        return max;
    }
    private int longestPath(TreeNode node){
        if(node==null)return -1;
        int leftPath=longestPath(node.left);
        int rightPath=longestPath(node.right);
        max=Math.max(leftPath+rightPath,max);
        return Math.max(leftPath,rightPath)+1;
    }
}
