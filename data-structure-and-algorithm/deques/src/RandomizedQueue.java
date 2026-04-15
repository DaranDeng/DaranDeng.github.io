import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item>implements Iterable<Item> {
    // construct an empty randomized queue
    private Deque.Node first;
    public RandomizedQueue(){

    }

    // is the randomized queue empty?
    public boolean isEmpty()

    // return the number of items on the randomized queue
    public int size()

    // add the item
    public void enqueue(Item item)

    // remove and return a random item
    public Item dequeue()

    // return a random item (but do not remove it)
    public Item sample()

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator
            implements Iterator<Item> {
        private Deque.Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if(!hasNext()){
                throw new NoSuchElementException("no more elements");}
            Item item =current.item;
            current=current.next;
            return item;
        }
        public void remove(Deque.Node node){
            throw new UnsupportedOperationException();
        }
    }

    }

        // unit testing (required)
    public static void main(String[] args)

}
}
