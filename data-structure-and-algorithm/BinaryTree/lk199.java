package BinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lk199 {
    List<List<Integer>>ans=new ArrayList<>();
    List<Integer>a=new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        rightSide(root);
        for (int i = 0; i < ans.size(); i++) {
                a.add(ans.get(i).getLast());
        }
        return a;
    }
    public void rightSide(TreeNode root){
        if (root==null)return;
        Deque<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int size=q.size();
            List<Integer>l=new ArrayList<>();
            while (size>0){
                TreeNode node=q.poll();
                l.add(node.val);
                if(node.left!=null)q.offer(node.left);
                if(node.right!=null)q.offer(node.right);
                size--;
            }
            ans.add(l);
        }
    }
}
