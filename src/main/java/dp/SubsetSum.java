package dp;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a set of non negative numbers and a total, find if there exists a  subset in this set
 * whose sum is same as total.
 */
public class SubsetSum {
  public boolean subsetSum(int[] input, int sum) {
    boolean[][] partialSums = new boolean[input.length + 1][sum + 1];
    for (int c = 0; c <= sum; c++) {
      for (int r = 1; r <= input.length; r++) {
        if (c == 0) {
          partialSums[r][c] = true;
        } else {
          if (c < input[r - 1]) {
            partialSums[r][c] = partialSums[r - 1][c];
          } else {
            partialSums[r][c] = partialSums[r - 1][c] || partialSums[r - 1][c - input[r - 1]];
          }
        }
      }
    }
    Set<Integer> subset = new HashSet<>();
    int c = sum;
    for (int r = input.length; r > 0 && c > 0; r--) {
      while (!partialSums[r][c]) {
        c--;
      }
      if (!partialSums[r - 1][c]) {
        subset.add(input[r - 1]);
        c = c - input[r - 1];
      }
    }
    System.out.println(subset);
    return partialSums[input.length][sum];
  }

  public static void main(String[] args) {
    System.out.println(new SubsetSum().subsetSum(new int[]{2, 3, 7, 8, 10}, 11));
  }
}
