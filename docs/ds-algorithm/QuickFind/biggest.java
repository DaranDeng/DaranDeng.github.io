package QuickFind;

public class biggest {
        private int[]id;
        private int[]sz;
        private int[]max; // 每个根节点对应的集合中的最大值
        /**初始化*/
        public biggest(int N){
            id=new int[N];
            sz=new int[N];
            max=new int[N];
            for (int i = 0; i < N; i++) {
                id[i]=i;
                sz[i]=1;
                max[i]=i;
            }
        }
        /**quick find*/
        public boolean connected1(int p,int q){
            return root(p)==root(q);
        }
    public void union3(int p,int q){
        int i=root(p);
        int j=root(q);
        if(i==j)return;
        if(sz[i]<sz[j]){
            id[i]=j;sz[j]+=sz[i];
            max[j]=Math.max(max[i], max[j]);
        }else {
            id[j]=i;sz[i]+=sz[j];
            max[i]=Math.max(max[i], max[j]);
        }
    }
    private int root(int i){
        while (i!=id[i])i=id[i];//只有根节点i==id[i]
        //i=id[i]意思是上移一层。
        return i;
    }
    public int findMax(int i) {
        return max[root(i)];
    }

    public static void main(String[] args) {
        biggest b=new biggest(10);
        b.union3(1,2);
        b.union3(7,2);
        b.union3(5,1);
        System.out.println(b.findMax(2));
    }
}
