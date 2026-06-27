package linkedlist;

public class lk876 {
    public ListNode middleNode(ListNode head) {
        if(head==null)return null;
        int length=0;
        int n=0;
        ListNode cur=head;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        cur=head;
        while (n<length/2){
            cur=cur.next;
            n++;
        }
        return cur;
    }
}
