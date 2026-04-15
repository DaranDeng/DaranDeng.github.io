package BinaryTree;

public class lk404 {
    /**函数会检查当前遍历到的节点有没有左叶子节点*，因为左叶子节点必须是子节点。
     * 不能自己判断自己。只能依靠其父节点来判断
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null)return 0;
        if(root.left==null&&root.right==null)return 0;
        int leftvalue=sumOfLeftLeaves(root.left);
        int rightvalue=sumOfLeftLeaves(root.right);
        if(root.left!=null&&root.left.left==null&&root.left.right==null)
            leftvalue=root.left.val;
        int sum=leftvalue+rightvalue;
        return sum;
    }
}
