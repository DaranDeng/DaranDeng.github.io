package BinaryTree;

public class lk530 {
    int res=Integer.MAX_VALUE;
    TreeNode pre=null;
    public int getMinimumDifference(TreeNode root) {
        getmini(root);
        return res;
    }

    public void getmini(TreeNode cur){
        if (cur==null)return;
        getmini(cur.left);
        if(pre!=null)
            res=Math.min(res,Math.abs(cur.val- pre.val));
        pre=cur;
        getmini(cur.right);
    }
}
