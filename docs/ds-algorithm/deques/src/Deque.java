import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException(
                    "cannot add a null item");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) last = first;
        else first.next = oldfirst;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException(
                    "cannot add a null item");
        Node oldlast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("empty deque");
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("empty deque");
        Item item = last.item;
        /**last=last.next;
         size--;
         if(isEmpty())first=null;
         return item;
         你的代码直接让 last = last.next，
         但 last.next 实际上是 null（因为
         last 是最后一个节点），所以这会导致
         last 变为 null，而不会正确地找到倒数第二个节点。*/
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node current = first;
            while (current.next != last) {
                current = current.next;
            }
            current.next = null;
            last = current;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if(!hasNext()){
            throw new NoSuchElementException("no more elements");}
            Item item = current.item;
            current=current.next;
            return item;
        }
        public void remove(Node node){
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer>deque=new Deque<>();
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(8);
        deque.addLast(9);
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());
        for (int i:deque){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println(deque.removeFirst());
        for (int i:deque){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println(deque.removeLast());
        for (int i:deque){
            System.out.print(i+" ");
        }
    }


      class Node {
        Node next;
        Item item;

        public Node() {
        }
    }
}

