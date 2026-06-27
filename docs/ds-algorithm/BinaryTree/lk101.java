package BinaryTree;

import java.util.Queue;
import java.util.Stack;

public class lk101 {
    /**Stack <Integer>stack=new Stack<>();
    public boolean isSymmetric(TreeNode root) {
        if(root==null)return true;
        inOrder(root);
        int[]arr1=new int[stack.size()];
        boolean flag=true;
        for (int i = 0; i < arr1.length; i++) {
            arr1[i]=stack.pop();
        }
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i]!=arr1[arr1.length-1-i]){
                flag=false;
                break;
            }
        }
        return flag;

    }
    public void inOrder(TreeNode root){
        if(root==null)return;
        inOrder(root.left);
        stack.push(root.val);
        inOrder(root.right);
    }树的每个节点值的对称性，而结构不能保证，但不妨为单纯检查树的节点值的问题
     提供了思路*/
    public boolean isSymmetric(TreeNode root) {
        return check(root.left,root.right);
    }
    public boolean check(TreeNode p,TreeNode q){
        if(p==null&&q==null)return true;
        if(p==null||q==null)return false;
        return p.val== q.val&&check(p.left,q.right)
                &&check(p.right,q.left);//模板
    }
    }
