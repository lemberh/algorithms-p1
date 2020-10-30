/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int count = 0;
    private Object[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = new Object[8];
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
        // System.out.println(count);
        if (queue.length <= count) {
            upscale();
        }
        queue[count] = item;
        count++;
    }

    // remove and return a random item
    public Item dequeue() {
        int index;
        Object toReturn;
        do {
            index = StdRandom.uniform(0, count);
            toReturn = queue[index];
        } while (toReturn == null);

        queue[index] = null;
        count--;

        if (count > 4 && count <= queue.length / 4) {
            downscale();
        }

        return (Item) toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int index = StdRandom.uniform(0, count);
        return (Item) queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator(queue, count);
    }

    private void upscale() {
        // System.out.println(count);
        int newSize = queue.length * 2;
        StdOut.println("olsSize:" + queue.length + "  new size:" + newSize);
        Object[] newQ = new Object[newSize];
        // copy data to new upscaled array
        for (int i = 0; i < queue.length; i++) {
            newQ[i] = queue[i];
        }
        queue = newQ;
    }

    private void downscale() {
        int newSize = queue.length / 2;
        StdOut.println("olsSize:" + queue.length + "  new size:" + newSize);
        Object[] newQ = new Object[newSize];
        // copy data to new upscaled array
        for (int i = 0, j = 0; i < queue.length; i++) {
            Object item = queue[i];
            if (item != null) {
                newQ[j++] = item;
            }
        }
        queue = newQ;
    }

    private static class RQIterator<T> implements Iterator<T> {

        private Object[] outOrder;
        private int index = 0;

        RQIterator(Object[] queue, int count) {
            outOrder = new Object[count];

            for (int i = 0; i < queue.length; i++) {
                Object item = queue[i];
                if (item != null) {
                    int j;
                    Object check;
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
            return (T) outOrder[index++];
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
