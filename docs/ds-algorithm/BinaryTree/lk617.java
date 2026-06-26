package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class lk617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1==null)return root2;
        if(root2==null)return root1;
        root1.val+=root2.val;
        root1.left=mergeTrees(root1.left,root2.left);
        root1.right=mergeTrees(root1.right,root2.right);
        return root1;
    }
    //选择将第二棵树合并到第一棵树上更简单，不必重新造一棵树
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        /*if(root1==null&&root2==null)return null;
        if(root1==null)return root2;
        if(root2==null)return root1;**/
        //没发现第二条如果root2为null，返回的也是null吗
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        Stack<TreeNode> q = new Stack<>();
        q.push(root1);
        q.push(root2);
        while (!q.isEmpty()) {
            TreeNode node2 = q.pop();
            TreeNode node1 = q.pop();
            node1.val += node2.val;
            //if (node1 == null && node2 == null) break;
            //为什么不像上面检查是否为空，对于根节点，开始就检查过了。
            //对于之后的节点，下面会检查。如果只是检查当下访问的节点，
            //就不会有递推了
            if (node1.left != null && node2.left != null) {
                q.push(node1.left);
                q.push(node2.left);
            } else if (node1.left == null) node1.left = node2.left;
            if (node1.right != null && node2.right != null) {
                q.push(node1.right);
                q.push(node2.right);
            } else if (node1.right == null) node1.right = node2.right;
        }
        return root1;
    }
}
