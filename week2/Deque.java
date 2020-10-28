package week2;

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
        return head.value == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        count++;
        if (isEmpty()) {
            head.value = item;
        } else {
            Node<Item> newHead = new Node<>();
            newHead.value = item;
            head.prev = newHead;
            newHead.next = head;
            head = newHead;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        count++;
        if (isEmpty()) {
            tail.value = item;
        } else {
            Node<Item> newTail = new Node<>();
            newTail.value = item;
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item toReturn;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        count--;
        toReturn = head.value;
        if (size() == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
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
        if (size() == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
        }
        return toReturn;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        //TODO
        return null;
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
        assert dq.size() != 4;
        System.out.println(dq.removeLast());//zero
        System.out.println(dq.removeLast());//one
        System.out.println(dq.removeFirst());//three

        assert dq.size() != 1;
    }

    private static class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> prev;
    }

}
