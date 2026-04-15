package QuickFind;

public class SocialNet {
    int []id;
    int []sz;
    int count;

    public SocialNet(int n) {
        id=new int[n];
        sz=new int[n];
        count=n;
        for (int j = 0; j < n; j++) {
            id[j]=j;
            sz[j]=1;
        }
    }
    public int root(int i){
        while (id[i]!=i){
            id[i]=id[id[i]];
            i=id[i];
        }
        return i;
    }
    public boolean connected(int p,int q){
        return root(p)==root(q);
    }
    public void union(int p,int q){
        int i=root(p);
        int j=root(q);
        if(sz[i]<sz[j]){
            id[i]=j;
            sz[j]+=sz[i];
        }else {
            id[j]=i;
            sz[i] += sz[j];
        }
        if(connected(i,j))
            count--;
    }
     static class friendship{
        int t;
        int m1;
        int m2;

        public friendship(int t,int m1, int m2) {
            this.t=t;
            this.m1 = m1;
            this.m2 = m2;
        }
    }
        public static int findTime(int n,friendship[]f){
            SocialNet socialNet=new SocialNet(n);
            for(friendship friendship:f){
                socialNet.union(friendship.m1,friendship.m2);
                if(socialNet.count==1)return friendship.t;
            }
            return -1;
        }

    public static void main(String[] args) {
        int n=5;
        friendship []friendship={
                new friendship(1,2,1),
                new friendship(2,3,4),
                new friendship(3,4,1),
                new friendship(4,0,2),
                new friendship(5,4,2),
        };
        System.out.println("earliest time:"+findTime(n,friendship));



    }

}

