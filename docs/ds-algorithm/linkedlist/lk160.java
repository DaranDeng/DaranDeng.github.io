package linkedlist;

public class lk160 {
    public ListNode xiangjiao(ListNode headA, ListNode headB){
            ListNode cura=headA;
            ListNode curb=headA;
            int countA=0;
            int countB=0;
            while (cura.next!=null){
                cura=cura.next;
                countA++;
        }
            while (curb.next!=null){
                curb=curb.next;
                countB++;
            }
            if (cura==curb)return null;
            int step=Math.abs(countA-countB);
            if (countA>countB){
                while (step>0){
                    headA=headA.next;
                    step--;
                }
            } else if (countA<countB) {
                while (step>0){
                    headB=headB.next;
                    step--;
                }
            }
            while (headA!=headB){
                headA=headA.next;
                headB=headB.next;
            }
            return headA;
    }
}
