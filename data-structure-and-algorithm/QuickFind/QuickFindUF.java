package QuickFind;

public class QuickFindUF {
    private int[]id;
    private int[]sz;
    /**初始化*/
    public QuickFindUF(int N){
        id=new int[N];
        sz=new int[N];
        for (int i = 0; i < N; i++) {
            id[i]=i;
            sz[i]=1;
        }
    }
    /**quick find*/
    public boolean connected1(int p,int q){
        return id[p]==id[q];
    }
    public void union1(int p,int q){
        int pid=id[p];
        int qid=id[q];
        for (int i = 0; i < id.length; i++) {
            if(id[i]==pid)id[i]=qid;
        }
    }

    /**quick union*/
    /**返回根节点*/
    /**public QuickFind.QuickFindUF(int N){
        id=new int[N];
        for (int i = 0; i < N; i++) {
            id[i]=i;
        }
    }*/
    private int root(int i){
        while (i!=id[i])i=id[i];//只有根节点i==id[i]
        //i=id[i]意思是上移一层。
        return i;
    }
    // 查找根节点（带路径压缩）
    private int rootCompression(int i){
        while (i!=id[i]){
            id[i]=id[id[i]];//make every other node in path
            //point to its grandparent
            i=id[i];
        }
        return i;
    }

        /**find*/
    public boolean connected2(int p,int q){
        return root(p)==root(q);
    }
    public void union2(int p,int q){
        int i=root(p);
        int j=root(q);
        //i=j;连接两个根节点
        id[i]=j;
    }
    /**加权快速合并算法，与上面的快速合并共用connect方法与root方法。
     * sz[i]表示根节点为i的树的对象数量，小树永远放下面*/
    public void union3(int p,int q){
        int i=root(p);
        int j=root(q);
        if(i==j)return;
        if(sz[i]<sz[j]){
            id[i]=j;sz[j]+=sz[i];
        }else {
            id[j]=i;sz[i]+=sz[j];
        }
    }

}
