package linkedlist;
/**约瑟夫问题，
 * 创建一个单向环形链表思路：先创建第一个节点，让first指向
 * 该节点，形成闭环。然后每当我们创建一个新的节点
 * 就把该节点加入到已有的环形链表中即可*/
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList c=new CircleSingleLinkedList();
        c.addBoy(4);
        c.showBoy();
        c.outCycle(5,1,2);
    }
}
/**如何创建新的节点：
 * 1 让当前的节点指向下一个新节点
 * 2 让新节点指向first，形成闭环
 * 3 将辅助指针移动至下一个*/
class CircleSingleLinkedList{
    private Boy first = null;
    public void addBoy(int nums){
        if(nums<1){
            System.out.println("the number is wrong");
            return;
        }
        Boy curBoy=null;
        for (int i = 1; i <= nums; i++) {
            Boy boy=new Boy(i);
            /**在方法体里面初始化first，会影响外部的first，因为
             * 它是成员变量Java 是值传递，但对象引用也是按
             * 值传递的。也就是说，方法内部接收到的是 first
             * 引用的副本，但副本和原始引用指向的是同一个对象*/
            if(i==1){
                first=boy;
                first.setNext(first);
                curBoy=first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }
    public void showBoy(){
        if(first==null) {
            System.out.println("empty ");
            return;
        }
        Boy curBoy=first;
        while (true){
            //Boy curBoy=first;如果在这，那么每次循环都会将
            //辅助指针curBoy归位，无限循环除非只有一个boy
            System.out.printf("the no of boys is %d "+"\n"
                    ,curBoy.getNo());
            if(curBoy.getNext()==first)break;
            curBoy=curBoy.getNext();
        }
    }
    /**1 */
    public void outCycle(int total,int startno,int m){
        //Boy helper=new Boy(total);这个是建立了无关节点，
        //并没有并入环中，单纯有一个编号罢了
        if(startno>total||first==null||startno<1){
            System.out.println("参数输入有误");
            return;
        }
        Boy helper=first;
        Boy start=first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        /**报数前先移动到第一个报数的小孩那里*/
        for (int i = 0; i < startno-1; i++) {
            helper = helper.getNext();
            start=start.getNext();
        }
        /**让start与helper移动m-1次*/
        while (true){
            for (int i = 0; i < m-1; i++) {
                start=start.getNext();
                helper=helper.getNext();
            }
            System.out.printf(
                    "the outcycle boy is %d\n",start.getNo());
            start=start.getNext();
            helper.setNext(start);
            if(start==helper) {// 最后剩下的节点
                System.out.printf("最后的编号：%d\n", first.getNo());
            break;}
        }

    }
}
    class  Boy {
        private int no;
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }


        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }

