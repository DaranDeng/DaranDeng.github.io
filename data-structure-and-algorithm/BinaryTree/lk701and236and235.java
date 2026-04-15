package BinaryTree;

import java.util.Stack;

public class lk701and236and235 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null)return new TreeNode(val);// 访问新节点（相当于“处理当前节点”）
        if(root.val<val){
            root.right=insertIntoBST(root.right,val);// 递归左子树
        }else{
            root.left=insertIntoBST(root.left,val);// 递归右子树
        }
        return root;
    }
    public boolean searchNode(TreeNode root,TreeNode node){
        if (root==null)return false;
        if(root.val==node.val)return true;
        return searchNode(root.left,node)||searchNode(root.right,node);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == q || root == p || root == null) return root;
        TreeNode l=lowestCommonAncestor(root.left,p,q);
        TreeNode r=lowestCommonAncestor(root.right,p,q);
        if(l==null&&r!=null){
            return r;
        } else if (l!=null&&r==null){
            return l;
        } else if (l!=null&&r!=null) {
            return root;
        }else return null;
    //如果左子树搜索返回true（找到节点），则直接返回 true，不再执行右子树搜索（||短路特性）
    //如果左子树返回false，会继续执行右子树搜索，只有两者都为 false 时才最终返回 false
    }
    public TreeNode lowestCommonAncestor235(TreeNode root, TreeNode p, TreeNode q) {
        /**二叉搜索树中的最近公共祖先
        通过比较当前遍历节点与p,q的大小，可以决定遍历走向，因此没有传统的遍历顺序
        对于当前节点的处理：若值处于p,q之间，则直接返回，从上向下遍历保证了一定是第一个出现的公共祖先
        终止条件：无需返回节点为Null的情况，二叉树里面一定有公共节点，遍历不到空值，
         所以当前节点若满足条件就是终止信号
         这个函数的意思是在以root为根节点的树里面找到p,q的最近祖先，所以我们默认
         return lowestCommonAncestor235(root.left,p,q);已经找到了，直接返回
        */
        if(root.val>Math.max(p.val, q.val)){
            return lowestCommonAncestor235(root.left,p,q);
        } else if (root.val<Math.min(p.val,q.val)) {
            return lowestCommonAncestor235(root.right,p,q);
        }else return root;
    }
    public TreeNode diedai(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null)return null;
        Stack<TreeNode>s=new Stack<>();
        Stack<TreeNode>s2=new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode node=s.pop();
            s2.push(node);
            if (node.val > Math.max(p.val, q.val)) {
                s.push(node.left);
            } else if (node.val < Math.min(p.val, q.val)) {
                s.push(node.right);
            }
        }
        return s2.pop();
    }
    }
