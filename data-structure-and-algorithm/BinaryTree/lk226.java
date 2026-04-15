package BinaryTree;

public class lk226 {
    public TreeNode invertTree(TreeNode root) {
        if (root==null)return root;
        TreeNode t;
        t=root.left;
        root.left=root.right;
        root.right=t;
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }
    }
