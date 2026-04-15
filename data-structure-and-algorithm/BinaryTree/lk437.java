package BinaryTree;

public class lk437 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */     public int pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            int ret = rootSum(root, targetSum);
            ret += pathSum(root.left, targetSum);
            ret += pathSum(root.right, targetSum);
            return ret;
        }
        public int rootSum(TreeNode p, int targetSum){
            if(p==null)return 0;
            int val=p.val;
            /**if(p.left==null&&p.right==null&&val==targetSum)
                return 1;这一步为什么错，因为只在叶子节点检查
             val == targetSum，但路径的中间节点也可能满足条件。
             应该在每一步都检查 val == targetSum，而不仅仅是叶子节点。
            return rootSum(p.left,targetSum-val)+rootSum(p.right,targetSum-val);*/
            int ret=0;
            if(val==targetSum)ret++;
            ret += rootSum(p.left, targetSum - p.val);
            ret += rootSum(p.right, targetSum - p.val);
            return ret;
        }
}
//rootSum(p, target)：
//
//作用：计算以节点 p 为起点，向下延伸的所有路径中，路径和等于 target 的数量。
//
//实现方式：
//
//递归检查当前路径和是否满足 target（通过逐步减去 p.val）。
//
//关键点：在递归的每一步都检查当前节点的值是否等于剩余的 target（即 p.val == target），而不仅仅是叶子节点。
//
//累加左子树和右子树的结果。
//
//pathSum(root, target)：
//
//作用：统计树中所有满足条件的路径总数（路径不一定从根节点开始）。
//
//实现方式：
//
//对每个节点都调用 rootSum(p, target)，计算以该节点为起点的合法路径数。
//
//递归处理左子树和右子树，确保覆盖所有可能的路径起点。
