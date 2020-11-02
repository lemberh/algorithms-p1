/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

// package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int count = 0;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[8];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // System.out.println(count);
        if (queue.length <= count) {
            upscale();
        }
        queue[count] = item;
        count++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, count);
        Item toReturn = queue[index];

        int lastTemIndex = count - 1;
        if (index != lastTemIndex) {
            queue[index] = queue[lastTemIndex];
            queue[lastTemIndex] = null;
        } else {
            queue[index] = null;
        }
        count--;

        if (count > 4 && count <= queue.length / 4) {
            downscale();
        }

        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (count == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, count);
        Item item = queue[index];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator<Item>(queue, count);
    }

    private void upscale() {
        // System.out.println(count);
        int newSize = queue.length * 2;
        // StdOut.println("olsSize:" + queue.length + "  new size:" + newSize);
        Item[] newQ = (Item[]) new Object[newSize];
        // copy data to new upscaled array
        for (int i = 0; i < queue.length; i++) {
            newQ[i] = queue[i];
        }
        queue = newQ;
    }

    private void downscale() {
        int newSize = queue.length / 2;
        // StdOut.println("olsSize:" + queue.length + "  new size:" + newSize);
        Item[] newQ = (Item[]) new Object[newSize];
        // copy data to new upscaled array
        int j = 0;
        for (int i = 0; i < queue.length; i++) {
            Item item = queue[i];
            if (item != null) {
                newQ[j++] = item;
            }
        }
        queue = newQ;
    }

    private static class RQIterator<T> implements Iterator<T> {

        private T[] outOrder;
        private int index = 0;

        RQIterator(T[] queue, int count) {
            outOrder = (T[]) new Object[count];

            for (int i = 0; i < queue.length; i++) {
                T item = queue[i];
                if (item != null) {
                    int j;
                    T check;
                    do {
                        j = StdRandom.uniform(0, outOrder.length);
                        check = outOrder[j];
                    } while (check != null);
                    outOrder[j] = item;
                }
            }
        }

        public boolean hasNext() {
            return index < outOrder.length;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return outOrder[index++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        for (int i = 0; i < 34; i++) {
            q.enqueue(i);
        }

        // for (int i = 0; i < q.size(); i++) {
        //     System.out.println(i + " - " +q.sample());
        // }

        for (Integer obj : q) {
            System.out.println(obj);
        }

        // int i = 0;
        // while (!q.isEmpty()) {
        //     System.out.println(i++ + " - " + q.dequeue());
        // }
    }

}
