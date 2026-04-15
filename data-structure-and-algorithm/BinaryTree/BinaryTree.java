package BinaryTree;

import java.util.Arrays;

/**如何用中序与后序遍历来构造二叉树？
 * 通过后序找到根节点，分割中序
 * 通过被分割的中序找到左区间的长度，再以此为分割后序
 * 递归构建左子树和右子树*/
public class BinaryTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder.length==0)return null;
        int rootvalue=postorder[postorder.length-1];
        TreeNode root=new TreeNode(rootvalue);
        if(postorder.length==1)return root;
        int index=0;
        for ( index = 0; index <inorder.length ; index++) {
            if (inorder[index]==rootvalue)break;
        }
        // 分割中序数组
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
        int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);

        // 分割后序数组
        int[] leftPostorder = Arrays.copyOfRange(postorder, 0, leftInorder.length);
        int[] rightPostorder = Arrays.copyOfRange(postorder, leftInorder.length, postorder.length - 1);
        root.left = buildTree(leftInorder, leftPostorder);
        root.right = buildTree(rightInorder, rightPostorder);
        return root;
    }
}
