package week2;/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;

    private int count = 0;

    // construct an empty deque
    public Deque(){
        head = new Node<>();
        tail = head;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return head.value == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException();
        count++;
        if (isEmpty()){
            head.value = item;
        }else{
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
        if (isEmpty()){
            tail.value = item;
        }else{
            Node<Item> newTail = new Node<>();
            newTail.value = item;
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
    }

    // remove and return the item from the front
    public Item removeFirst(){

        count--
    }

    // remove and return the item from the back
    public Item removeLast(){

        count--
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args);

    private static class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> prev;
    }

}
