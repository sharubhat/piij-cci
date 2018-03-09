package topcoder;

import java.util.*;

public class TallPeople {
  public int[] getPeople(String[] people) {
    List<List<Integer>> heights = new ArrayList<>();
    for (int r = 0; r < people.length; r++) {
      heights.add(r, new ArrayList<>());
      for (String h : people[r].split(" ")) {
        heights.get(r).add(Integer.parseInt(h));
      }
    }

    int tRow = Integer.MIN_VALUE;
    for (List<Integer> row : heights) {
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (int i : row) {
        pq.add(i);
      }
      if (pq.peek() >= tRow) {
        tRow = pq.poll();
      }
    }

    int tCol = Integer.MAX_VALUE;
    for (int c = 0; c < heights.get(0).size(); c++) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return o2.compareTo(o1);
        }
      });
      for ( int r = 0; r  < heights.size(); r++) {
        pq.add(heights.get(r).get(c));
      }
      if (pq.peek() <= tCol) {
        tCol = pq.poll();
      }
    }

    return new int[]{tRow, tCol};
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new TallPeople().getPeople(new String[]{"9 2 3", "4 8 7"})));
    System.out.println(Arrays.toString(new TallPeople().getPeople(new String[]{"1 2", "4 5", "3 6"})));
  }
}
