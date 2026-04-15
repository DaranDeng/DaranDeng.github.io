package BinaryTree;

public class PathSum {
    public static boolean hasPath(TreeNode node,int sum){
        if(node==null)return false;
        if (node.left==null&&node.right==null)return sum== node.val;
        sum-=node.val;
        return hasPath(node.right,sum)||hasPath(node.left,sum);
    }

    public static void main(String[] args) {
        TreeNode node=new TreeNode(5,new TreeNode(4),new TreeNode(7));
        System.out.println(hasPath(node,6));
    }
}
