package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1=new HeroNode2(1,"宋江");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义");
        HeroNode2 hero3=new HeroNode2(3,"晁盖");
        DoubleLinkedList s=new DoubleLinkedList();
        s.add(hero1);
        s.add(hero2);
        s.add(hero3);
        s.list();
        s.remove(hero1);
        s.list();
        HeroNode2 newhero=new HeroNode2(2,"鲁智深");
        s.addOrder(newhero);
        s.list();
    }
}
class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"");//初始化
    public HeroNode2 getHead(){
        return head;
    }
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;//不能直接动头节点
        while (true){
            if(temp==null)break;
            System.out.println(temp);//会自动调用toString方法
            temp=temp.next;
        }
    }
    public void add(HeroNode2 heroNode){
        HeroNode2 temp=head;//不能直接动头节点，因为只存储next，无数据
        /*如何添加节点，先找到原链表的尾节点，然后再让其指针指向新的节点**/
        while (true){
            if(temp.next==null)break;
            temp=temp.next;
        }
        temp.next=heroNode;
        heroNode.pre=temp;//把新节点赋给了temp.next
    }
    public void addOrder(HeroNode2 heroNode){
        boolean flag=false;
        HeroNode2 temp=head;
        while (true){
            /*if(temp.next.no>heroNode.no)break;
            if(temp.next==null)break;可以这样吗，第一个
            条件可能会是temp.next=null啊**/
            if(temp.next==null)break;
            if(temp.next.no>heroNode.no)break;//找到要插入的位置，在temp与
            //temp。next之间
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
            heroNode.pre=temp;
            if(temp.next!=null){
            heroNode=temp.next.pre;
            }
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
    public void remove(HeroNode2 heroNode){
        HeroNode2 temp=head.next;//temp是辅助节点来一个一个遍历的找,可以自删除
        boolean fl=false;//标志是否找到
        while (true){
            if(temp.next==null) {System.out.println("链表为空");
                return;}
            if (heroNode.no==temp.no){
                fl=true;break;
            }
            temp=temp.next;
        }
        if(fl){
            temp.pre.next=temp.next;
            if(temp.next!=null)temp.next.pre=temp.pre;//这个很关键，因为如果是最后一个节点，那么temp.next=null，
            //null.pre也是null会引发空指针异常，但temp.pre.next=temp.next可以，就是temp.pre.next=null啊
        }else System.out.format("未找到编号为%d的节点",heroNode.no);
    }
}
class HeroNode2{
    public int no;
    public String name;
    public HeroNode2 next;
    public HeroNode2 pre;//指向前一个节点默认为null
    public HeroNode2(int no,String name){
        this.no=no;
        this.name=name;
    }
    /*若无，只会打印默认的对象哈希值**/
    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
    }
}