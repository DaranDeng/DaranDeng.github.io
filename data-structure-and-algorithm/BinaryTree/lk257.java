package BinaryTree;

import java.util.ArrayList;
import java.util.List;
/**将"找出所有路径"拆解为"当前节点值 + 左子树所有路径"
 * 和"当前节点值 + 右子树所有路径"*/
public class lk257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String>ans=new ArrayList<>();
        getPath(root,"",ans);
        return ans;
    }
    /**能否
     * public void getPath(TreeNode root,List<String>ans){
     *         if (root==null)return;
     *         String path="";
     *         if (root.left==null&&root.right==null) {
     *             path+=root.val;
     *             ans.add(path);
     *         }else {
     *             path += root.val;
     *             path += "->";
     *         }
     *         getPath(root.left,ans);
     *         getPath(root.right,ans);
     *     }
     *     1每次递归都会清空path。
     *     2path更新后没有传递给子节点递归调用
     *     3所以遇到这种情况就给函数加一个参数就完了*/
    public void getPath(TreeNode root,String path,List<String>ans){
        if(root==null)return ;
        path=path+root.val;
        if (root.left==null&&root.right==null) {
            ans.add(path);
        }else path+="->";
        getPath(root.left,path,ans);
        getPath(root.right,path,ans);
    }
}
