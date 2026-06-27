package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class lknew257 {
    public  List<String> fun(TreeNode root){
        List<String>ans=new ArrayList<>();
        getPath(root,ans);
        return ans;
    }
    public void getPath(TreeNode root,List<String>ans){
        if (root==null)return;
        String path="";
        if (root.left==null&&root.right==null) {
            path+=root.val;
            ans.add(path);
        }else {
            path += root.val;
            path += "->";
        }
        getPath(root.left,ans);
        getPath(root.right,ans);
    }
}
