package linkedlist;

public class SingleLinkedList {
    public static void main(String[] args) {
        HeroNode hero1=new HeroNode(1,"宋江");
        HeroNode hero2=new HeroNode(2,"卢俊义");
        HeroNode hero3=new HeroNode(3,"晁盖");
        Linkedlist s=new Linkedlist();
        s.addOrder(hero2);
        s.addOrder(hero1);
        s.addOrder(hero3);
        s.addOrder(hero3);
        s.list();
        /*HeroNode newhero=new HeroNode(2,"卢俊");
        s.update(newhero);**/
        s.remove(hero3);
        s.list();
    }
}
class Linkedlist{
    private HeroNode head=new HeroNode(0,"");//初始化
    public void add(HeroNode heroNode){
        HeroNode temp=head;//不能直接动头节点，因为只存储next，无数据
        /*如何添加节点，先找到原链表的尾节点，然后再让其指针指向新的节点**/
        while (true){
            if(temp.next==null)break;
            temp=temp.next;
        }
        temp.next=heroNode;//把新节点赋给了temp.next
    }
    /**先找到想删除的英雄的前一个节点，然后再删*/
    public void remove(HeroNode heroNode){
        HeroNode temp=head;//temp是辅助节点来一个一个遍历的找
        boolean fl=false;//标志是否找到
        while (true){
            if(temp.next==null)break;
            if (heroNode.no==temp.next.no){
                fl=true;break;
            }
        temp=temp.next;
        }
        if(fl){
            temp.next=temp.next.next;
        }else System.out.format("未找到编号为%d的节点",heroNode.no);
    }
    /**第二种方法：按顺序根据排名将英雄插入到指定位置*/
    /**先找到*/
    public void addOrder(HeroNode heroNode){
        boolean flag=false;
        HeroNode temp=head;
        while (true){
            /*if(temp.next.no>heroNode.no)break;
            if(temp.next==null)break;可以这样吗，第一个
            条件可能会是temp.next=null啊**/
            if(temp.next==null)break;
            if(temp.next.no>heroNode.no)break;
            if(temp.next.no==heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.printf("想插入的英雄序号%d已有，" +
                    "不能加入\n",heroNode.no);
        }else{
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
    public void update(HeroNode newheroNode){
        //判断链表是否为空
        if(head.next==null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义辅助变量
        HeroNode temp=head.next;
        boolean flag=false;
        while (true) {
            if (temp == null) break;//已遍历完

            if (temp.no == newheroNode.no) {
                flag = true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name= newheroNode.name;
        }else {
            System.out.printf("没有找到%d的节点\n", newheroNode.no);
        }
    }
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;//不能直接动头节点
        while (true){
            if(temp==null)break;
            System.out.println(temp);//会自动调用toString方法
            temp=temp.next;
        }
    }
}
class HeroNode{
    public int no;
    public String name;
    public HeroNode next;
    public HeroNode(int no,String name){
        this.no=no;
        this.name=name;
    }
    /*若无，只会打印默认的对象哈希值**/
    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
    }
}
