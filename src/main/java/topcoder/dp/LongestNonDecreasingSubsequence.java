package topcoder.dp;

import java.util.Arrays;

public class LongestNonDecreasingSubsequence {
  public int lnds(int[] arr) {
    int[] lis = new int[arr.length];
    Arrays.fill(lis, 1);

    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[j] <= arr[i]) {
          lis[i] = Math.max(lis[i], lis[j] + 1);
        }
      }
      max = Math.max(max, lis[i]);
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(new LongestNonDecreasingSubsequence().lnds(new int[]{10, 22, 9, 33, 21, 50, 41, 60}));
  }
}
