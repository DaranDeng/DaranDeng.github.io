package BinaryTree;

import java.util.*;
/**迭代法两大步骤：访问节点，将元素值放入列表中。
 * 值得注意的是，入栈与出栈顺序
 * 在其他的问题中用迭代法可能不需要result这个列表
 * 或q栈,可以直接用辅助指针cur*/
public class diedaidigui {
    public List<Integer> diedai(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root==null)return result;
        Stack<TreeNode> q=new Stack<>();
        q.push(root);
        while (!q.isEmpty()){
            TreeNode node=q.pop();
            result.add(node.val);
            if(node.right!=null) q.push(node.right);
            if(node.left!=null) q.push(node.left);
        }
        return result;
    }
    /**中序遍历用迭代法：由于访问二叉树节点必涉及根节点，
     * 那么再看看中序遍历，中序遍历是左中右，先访问的是二叉树
     * 顶部的节点，然后一层一层向下访问，直到到达树左面的最底部，
     * 再开始处理节点（也就是在把节点的数值放进result数组中），
     * 这就造成了处理顺序和访问顺序是不一致的*/
    public List<Integer> midOrder(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root==null)return result;
        Stack<TreeNode> q=new Stack<>();
        TreeNode cur=root;//用来访问节点
        while (cur!=null||!q.isEmpty()){
            if(cur!=null){
                q.push(cur);
                cur=cur.left;
            }else {
                 cur = q.pop();
                 result.add(cur.val);
                 cur=cur.right;
            }
        }
        return result;
        }

    }
