package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class lkLCR193 {
    //经典寻找已知节点的路线
    public List<TreeNode> getPath(TreeNode root,TreeNode target){
        List<TreeNode>path=new ArrayList<>( );
        TreeNode node=root;
        while (node!=target){
            path.add(node);
            if (target.val<node.val){
                node=node.left;
            }else node=node.right;
        }
        path.add(node);
        return path;
    }
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        List<TreeNode>pathp=getPath(root,p);
        List<TreeNode>pathq=getPath(root,q);//list是先进先出
        TreeNode an=null;
        for (int i = 0; i < pathp.size()&&i< pathq.size(); i++) {
            if (pathp.get(i)==pathq.get(i))an=pathp.get(i);
            else break;
        }
        return an;
    }
}
