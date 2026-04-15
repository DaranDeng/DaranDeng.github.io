package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class lk112 {
    List<List<Integer>>ans=new ArrayList<>();
    List<Integer>l=new ArrayList<>();
    public boolean hasPathSum(TreeNode root, int targetSum) {
            if(root==null)return false;
            targetSum-=root.val;
            if(targetSum==0&&root.right==null&&root.left==null)
                return true;
            return hasPathSum(root.left,targetSum)||hasPathSum(root.right,targetSum);
        }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSum2(root,targetSum);
        return ans;

    }
    public void pathSum2(TreeNode root,int count){
        if(root==null)return;
        count-=root.val;
        l.add(root.val);
        if(count==0&&root.right==null&&root.left==null)
            //ans.add(l);会影响后续对l的操作。
            ans.add(new ArrayList<>(l));
        pathSum2(root.right,count);
        pathSum2(root.left,count);
        l.remove(l.size()-1);
    }
}
