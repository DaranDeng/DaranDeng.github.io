package BinaryTree;

//Key：用于排序和查找（必须是可比较的）。
//Value：存储关联的数据（可以
// 是任意类型，如 String, Integer, 甚至自定义对象）

public class Binary <Key extends Comparable<Key>,Value> {
    //extends这不是“继承” Comparable，而是约束泛型参数
    //Key 必须实现Comparable<Key> 接口
    private Node root;
    private int n;

    private class Node {
        public Key key;
        public Value value;
        public Node left;
        public Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public int size() {
        return n;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            n++;
            return new Node(key, value, null, null);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left=put(node.left, key, value);
        } else if (cmp > 0) {
            node.right=put(node.right, key, value);
        } else node.value=value;
        return node;
    }

    private Value get(Key key){
        Node x=root;
        while (x!=null){
            int cmp=key.compareTo(x.key);
            if (cmp<0){
                x=x.left;
            }else if (cmp>0){
                x=x.right;
            }else return x.value;
        }
        return null;
    }//都是通过key来查找
    public void delete( Key key){
        delete(root,key);
    }
    private Node delete(Node x,Key key){
        if (x==null)return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left=delete(x.left,key);
        } else if (cmp > 0) {
            x.right=delete(x.right,key);
        } else {
            n--;
            if (x.right==null)return x.left;
            if (x.left==null)return x.right;
            Node minNode=x.right;
            while (minNode.left!=null){
                minNode=minNode.left;
            }
            Node n=x.right;
            while (n.left!=null){
                if(n.left.left==null){
                    n.left=null;
                }else{
                    n=n.left;
                }
            }
            minNode.left=x.left;
            minNode.right=x.right;
            x=minNode;
        }
        return x;
    }
}
