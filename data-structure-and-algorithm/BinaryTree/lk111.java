package BinaryTree;

import java.util.Deque;
import java.util.LinkedList;

public class lk111 {
    public int minDepth(TreeNode root){
        if(root==null)return 0;
        if(root.right==null&&root.left==null)return 1;
        int l=minDepth(root.left);
        int r=minDepth(root.right);
        if(root.right==null||root.left==null)return l+r+1;
        return Math.min(minDepth(root.left),minDepth(root.right));
    }
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) q.offer(root.left);
                if (node.right != null) q.offer(root.right);
                size--;
            }
            depth++;
        }
        return depth;
    }
}
