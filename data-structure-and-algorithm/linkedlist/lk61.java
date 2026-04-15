package linkedlist;

public class lk61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||k<1)return head;
        if(head.next==null)return head;
        ListNode cur=head;
        ListNode newtail=head;
        ListNode newhead;
        int length=1;
        while(cur.next!=null){
            cur=cur.next;
            length++;
        }
        cur.next=head;
        for (int i = 0; i < length-k%length-1; i++) {
            newtail=newtail.next;
        }
        newhead=newtail.next;
        newtail.next=null;
        return newhead;
    }
}
