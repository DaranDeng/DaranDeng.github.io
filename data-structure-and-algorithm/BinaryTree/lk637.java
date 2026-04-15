package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lk637 {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> ans=new ArrayList<>();
            if (root==null)return ans;
            Queue<TreeNode> q=new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()){
                int size= q.size();
                int n=q.size();
                long sum=0;
                while (size>0){
                    TreeNode node=q.poll();
                    sum+=node.val;
                    if(node.left!=null)q.offer(node.left);
                    if(node.right!=null)q.offer(node.right);
                    size--;
                }
                ans.add(sum/(n*1.0));
            }
            return ans;
        }
    }
