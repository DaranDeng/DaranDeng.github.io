package linkedlist;

public class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    ListNode(int x) {
        val = x;
        next = null;
    }

    //前序遍历
    public ListNode reverseList(ListNode head) {
        return reverse1(null, head);
    }

    public ListNode reverse1(ListNode pre, ListNode node) {
        if (node == null) return pre;
        ListNode next = node.next;
        node.next = pre;
        return reverse1(node, next);
    }

    public ListNode reverse2(ListNode node) {
        if(node==null||node.next==null)return node;
        ListNode res=reverse2(node.next);
        node.next.next=node;
        node.next=null;
        return res;
    }
}

