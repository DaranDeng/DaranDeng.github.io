package linkedlist;
/**已知有环，*/
public class lk0142 {
    public ListNode detectCycle(ListNode head){
        ListNode fast=head;
        ListNode slow=head;
        while (true){
            //两个条件都要有
            if(slow.next==null||fast.next.next==null)return null;
            fast=fast.next.next;
            slow=slow.next;
            if (fast==slow)break;
        }
        slow=head;
        while (true){
            if (slow==fast)return fast;//直接返回指针，而且放在第一条
            slow=slow.next;
            fast=fast.next;
        }
    }
}
