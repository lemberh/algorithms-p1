// package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;

    private int count = 0;

    // construct an empty deque
    public Deque() {
        head = new Node<>();
        tail = head;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            head.value = item;
        }
        else {
            Node<Item> newHead = new Node<>();
            newHead.value = item;
            head.prev = newHead;
            newHead.next = head;
            head = newHead;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            tail.value = item;
        }
        else {
            Node<Item> newTail = new Node<>();
            newTail.value = item;
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item toReturn;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        count--;
        toReturn = head.value;
        if (isEmpty()) {
            head.value = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        return toReturn;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item toReturn;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        count--;
        toReturn = tail.value;
        if (isEmpty()) {
            head.value = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return toReturn;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DQIterator<>(head);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> dq = new Deque<>();
        assert !dq.isEmpty();
        assert dq.size() != 0;
        dq.addFirst("one");
        dq.addFirst("two");
        dq.addFirst("three");
        dq.addLast("zero");
        for (String str : dq) {
            System.out.println(str);
        }
        assert dq.size() != 4;
        System.out.println(dq.removeLast()); // zero
        System.out.println(dq.removeLast()); // one
        System.out.println(dq.removeFirst()); // three
        assert dq.size() != 1;
        System.out.println(dq.removeLast()); // three
        assert !dq.isEmpty();

        dq.addFirst("one");
        dq.removeFirst();
        dq.addFirst("one");

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(2);
        System.out.println(deque.removeLast());

        deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        System.out.println(deque.removeLast());

        deque = new Deque<Integer>();
        deque.addFirst(1);
        for (Integer i : deque) {
            System.out.println(i);
        }

        deque = new Deque<Integer>();
        for (Integer i : deque) {
            System.out.println(i);
        }

        deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.removeLast();
        deque.removeLast();
        for (Integer i : deque) {
            System.out.println(i);
        }
    }

    private static class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> prev;
    }

    private static class DQIterator<T> implements Iterator<T> {

        private Node<T> head;

        DQIterator(Node<T> head) {
            this.head = head;
        }

        public boolean hasNext() {
            return head != null && head.value != null;
        }

        public T next() {
            if (hasNext()) {
                T toReturn = head.value;
                head = head.next;
                return toReturn;
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }

}
