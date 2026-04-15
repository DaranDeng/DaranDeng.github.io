package linkedlist;

public class lk234 {
    public ListNode reverse(ListNode node){
        ListNode cur=node;
        ListNode pre=null;
        while (cur!=null){
          ListNode t= cur.next;
          cur.next=pre;
          pre=cur;
          cur=t;
        }
        return pre;
    }
    public boolean isPa(ListNode head){
        int count=0;
        ListNode cur=head;
        while (cur.next!=null){
            count++;
            cur=cur.next;
        }
        count=count/2;
        cur=head;
        while (count>0){
            count--;
            cur=cur.next;
        }
        ListNode rev=reverse(cur);
        while (rev.next!=null){
            if (cur.val!=head.val)return false;
            head=head.next;
            rev=rev.next;
        }
        return true;
    }
}
