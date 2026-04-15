package BinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        //给你头节点，拆解为计算其左子树与右子树的叶节点
        private static int CountLeaf(TreeNode node){
            if (node==null){
                return 0;
            }
            if (node.right==null&&node.left==null)return 1;
            return CountLeaf(node.right)+CountLeaf(node.right);
        }
        //要想计算第K层的节点数，必先计算第K-1层的每一个节点的左右子节点
        private static int CountK(TreeNode node,int k){
            if (node==null){
                return 0;
            }
            if(k==1)return 1;
            return CountK(node.right,k-1)+CountK(node.left,k-1);
        }
        //这个函数的定义是找到值为x的节点.有时要把函数本身的
        // 意思看成完成时，而不是进行时
        private static TreeNode SearchNode(TreeNode node,int x) {
            if (node == null) {
                return null;
            }
            if (node.val == x) return node;
            TreeNode leftNode = SearchNode(node.left, x);
            if (leftNode != null) return leftNode;
            TreeNode rightNode = SearchNode(node.right, x);
            if (rightNode != null) return rightNode;
            return null;// 左右子树都没找到，返回 null
        }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>>result=new ArrayList<>();
        if(root==null)return result;
        Map<Integer, List<Integer>> map = new HashMap<>();
        helper(root, 0, map);
        return new ArrayList<>(map.values());
    }
    public void helper(TreeNode root, Integer level,
                       Map<Integer,List<Integer>>map){
            if(root==null)return;
            if(map.get(level)==null)map.put(level,
                    new ArrayList<Integer>());
            List<Integer>list=map.get(level);
            //不是废话，因为在添加数值之前本来就为空列表，这是初始化
            list.add(root.val);
            helper(root.left,level+1,map);
            helper(root.right,level+1,map);
    }
    }

