package BinaryTree;

public class lk100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null)return true;
        if(p==null||q==null)return false;
        /**if(!isSameTree(p.right,q.right))return false;
        if(!isSameTree(p.left,q.left))return false;*/
        /**如果 右子树已经不同，就没必要再递归检查左子树了，
         * 但你的代码仍然会进入左子树的递归，导致不必要的计算。*/
        return (p.val==q.val)&&isSameTree(p.right,q.right)
        &&isSameTree(p.left,q.left);
    }
    }
