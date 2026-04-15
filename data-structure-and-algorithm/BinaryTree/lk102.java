package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lk102 {
    List<List<Integer>>ans=new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        checkFun01(root,0);
        return ans;
    }
    public void checkFun01(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;

        if (ans.size() < deep) {
            List<Integer> item = new ArrayList<Integer>();
            ans.add(item);
        }
        ans.get(deep - 1).add(node.val);

        checkFun01(node.left, deep);
        checkFun01(node.right, deep);
    }
    public void checkFun02(TreeNode root){
        if (root==null)return;
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int size= q.size();
            List<Integer>l=new ArrayList<>();
            while (size>0){
                TreeNode node=q.poll();
                if (node != null) {
                    l.add(node.val);
                }
                if(node.left!=null)q.offer(node.left);
                if(node.right!=null)q.offer(node.right);
                size--;
            }
            ans.add(l);
        }
    }
}
