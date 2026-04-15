package BinaryTree;

public class lk98 {
    long pre=Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        //还是拆分为两个子问题，并处理当前节点。只不过递归返回值为Boolean
        if (root == null) return true;
        // 访问当前节点：如果当前节点小于等于中序遍历
        // 的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        //这个要放在处理当前节点之后，并且上述都是false，要通过
        // 多个条件才可以结束函数
        return isValidBST(root.left)&&isValidBST(root.right);
    }
    /**解法二*/
    public boolean isValidBST2(TreeNode root){
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root,long lower,long upper) {
        if(root==null)return true;
        if(lower>=root.val||upper<= root.val)return false;
        return isValidBST(root.left,lower,root.val)
                &&isValidBST(root.right,root.val,upper);
    }
}
