package cp3.e123;

import java.util.PriorityQueue;

/**
 * Exercise 1.2.3-4 from book competitive programming 3
 *
 * Given n random integers, print the distinct (unique) integers in sorted order.
 */
public class DistinctSorted {
    public static void main(String[] args) {
        int[] inputs = {10, 15, 18, 10, 4, 5, 7, 4, 0};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i: inputs) {
            if (pq.isEmpty()) {
                pq.add(i);
            } else {
                if (pq.peek() != i) {
                    pq.add(i);
                }
            }
        }
        while (!pq.isEmpty()) {
            System.out.printf(pq.poll() + ", ");
        }
    }
}
