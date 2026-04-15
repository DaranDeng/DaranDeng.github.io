package linkedlist;

public class Soolution2 {
    public static void main(String[] args) {
        Soolution2 s=new Soolution2();
        ListNode2 head=new ListNode2
                (1,new ListNode2(2,new ListNode2(5)) );
        ListNode2 head2=new ListNode2
                (1,new ListNode2(3,new ListNode2(4)) );
        System.out.println(s.merge(head,head2));
        System.out.println(s.isPalindrome(head));

    }
    public ListNode2 merge(ListNode2 l1,ListNode2 l2) {
        ListNode2 dummy = new ListNode2();//虚拟头节点，合成后的新链的
        ListNode2 cur = dummy;//辅助指针
        /**现在有三个链：
         * 将原先两条链同时遍历，小的节点连入新链，并且小的移动
         * 节点用了才移动指针
         * 再与之前的大节点比较*/
        while (l1 != null && l2 != null) {
            if (l1.val> l2.val){
                cur.next=l2;//这是复用原节点，无需创造新节点
                l2=l2.next;
            }else {
                cur.next=l1;
                l1=l1.next;
            }
            cur=cur.next;//移动指针到新链表的当前末尾
        }
        cur.next=(l1!=null)? l1:l2;
        //将未遍历完的链表直接接到合并结果的末尾，因为
        //是先比再连所以直接接入,而且是将剩余的整个链表连入
        return dummy.next;
    }
    public ListNode2 reverseList(ListNode2 head){
        if(head==null){
            return null;
        }
        ListNode2 prev=null;
        ListNode2 t=null;
        /**head.next 是 head 指向的节点的 next 指针，它的值是下一个节点的地址。

         例如：若 head 指向 1，则 head.next 是 1 的 next 指针，指向 2。

         */
        while (head!=null){
            t=head.next;//head.next 是当前节点（head）的
            // next 指针，指向下一个节点，但它本身不是节点。
            head.next=prev;//是 head 的 next 指向 prev
            // （即当前节点反向指向前驱），不是“第二个节点”指向 prev
            prev=head;//前驱指针 prev 移动到当前节点,
            // 总是在移动指针操作的前一行
            head=t;//当前指针 head 移动到下一个待处理节点
        }
        return prev;
    }
        public boolean isPalindrome(ListNode2 head){
        if(head==null) System.out.println("empty");
        return head==reverseList(head);
    }
}
class ListNode2{
    int val;
    ListNode2 next;

    public ListNode2() {
    }

    public ListNode2(int val) {
        this.val = val;
    }

    public ListNode2(int val, ListNode2 next) {
        this.val = val;
        this.next = next;
    }
}