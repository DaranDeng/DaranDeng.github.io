package linkedlist;

public class lk24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy=new ListNode();
        dummy.next=head;
        ListNode cur=dummy;
        ListNode temp;
        ListNode temp1;
        while (cur.next!=null&&cur.next.next!=null){
            temp=cur.next;
            temp1=cur.next.next.next;
            cur.next=cur.next.next;
            cur.next.next=temp;
            temp.next=temp1;
            cur=cur.next.next;
        }
        return dummy.next;
    }
}
