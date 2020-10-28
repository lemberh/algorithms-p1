// ****************************************************************************
// *  Name:              Roman Nazarevych
// *  Coursera User ID:  123456
// *  Last modified:     1/1/2019
// ****************************************************************************

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int TOP = 0;

    private final int[][] grid;
    private final WeightedQuickUnionUF conn;
    private final UnionQF fullTree;
    private final int size;

    private final int bottom;
    private int openedCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        bottom = n * n + 1;
        size = n;
        grid = new int[n][n];

        int connSize = n * n + 2;
        conn = new WeightedQuickUnionUF(connSize);
        fullTree = new UnionQF(connSize);

        // connect all top line to virtual top
        // for (int i = 1; i < size + 1; i++) {
        //     conn.union(top, i);
        //     fullTree.union(top, i);
        // }
        // connect all bottom line to virtual bottom
        for (int i = connSize - size - 1; i < connSize - 1; i++) {
            conn.union(i, bottom);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        grid[row - 1][col - 1] = 1;
        openedCount++;

        int openedIndex = flatIndex(row, col);
        // check top
        if (row != 1 && isOpen(row - 1, col)) {
            int t = flatIndex(row - 1, col);
            conn.union(openedIndex, t);
            fullTree.union(openedIndex, t);
        }
        else if (row == 1) {
            conn.union(openedIndex, TOP);
            fullTree.union(openedIndex, TOP);
        }

        // check bottom
        if (row != size && isOpen(row + 1, col)) {
            int b = flatIndex(row + 1, col);
            conn.union(openedIndex, b);
            fullTree.union(openedIndex, b);
        }
        // check left
        if (col != 1 && isOpen(row, col - 1)) {
            int left = flatIndex(row, col - 1);
            conn.union(openedIndex, left);
            fullTree.union(openedIndex, left);
        }
        // check right
        if (col != size && isOpen(row, col + 1)) {
            int right = flatIndex(row, col + 1);
            conn.union(openedIndex, right);
            fullTree.union(openedIndex, right);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return isOpen(row, col) && fullTree.isConnected(TOP, flatIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return conn.find(TOP) == conn.find(bottom);
    }

    private void checkBounds(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) throw new IllegalArgumentException();
    }

    private int flatIndex(int row, int col) {
        return (row - 1) * size + (col - 1) + 1;
    }

    private static class UnionQF {

        private final int[] conn;
        private final int size;

        public UnionQF(int size) {
            this.size = size;
            conn = new int[size];
            for (int i = 0; i < size; i++) {
                conn[i] = i;
            }
        }

        private UnionQF(int[] conn) {
            this.conn = conn;
            size = conn.length;
        }

        public UnionQF copy() {
            int[] connCopy = new int[size];
            System.arraycopy(conn, 0, connCopy, 0, size);
            return new UnionQF(connCopy);
        }

        public void union(int a, int b) {
            int aRoot = findRoot(a);
            int bRoot = findRoot(b);
            // connect root of a to root of b
            conn[bRoot] = aRoot;
        }

        public boolean isConnected(int a, int b) {
            int aRoot = findRoot(a);
            int bRoot = findRoot(b);
            return aRoot == bRoot;
        }

        private int findRoot(int i) {
            int root = conn[i];
            while (root != i) {
                conn[i] = conn[root];
                i = root;
                root = conn[i];
            }
            return root;
        }
    }

}
