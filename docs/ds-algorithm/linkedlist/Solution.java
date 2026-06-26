package linkedlist;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1,ListNode l2) {
        /**
        if (l1 == null || l2 == null) {
            return null;
        }
        if ((l1.val == 0 && l1.next == null) || (l2.val == 0 && l2.next == null)) {
            return null;
        }
        int i = 0;
        int j = 0;
        int sum1 = 0;
        int sum2 = 0;
        while (l1.next != null) {
            sum1 += (int) (l1.val * Math.pow(10, i));
            i++;
        }
        while (l2.next != null) {
            sum2 += (int) (l2.val * Math.pow(10, j));
            j++;
        }

        return l1;
    }*/
    ListNode dummy=new ListNode();
    ListNode res=dummy;//虚拟头节点，最后不能返回它
    int sum=0;
    int ans;
    while (l1!=null||l2!=null|| sum != 0){
        /**不能忽略一个链表遍历完而另一个没有的情况
         * sum！=0的情况是最高位进1*/
        int val1 = (l1 != null) ? l1.val : 0;
        int val2 = (l2 != null) ? l2.val : 0;
        ans=val2+val1+sum;
        sum=ans/10;
        ans=ans%10;
        dummy.next=new ListNode(ans);
        dummy=dummy.next;
        if (l1 != null) l1 = l1.next;
        if (l2 != null) l2 = l2.next;
    }
    return res.next;
}
}
