package topcoder.dp;

import java.util.Arrays;

/**
 * There is a slight difference between longest increasing subsequence and length of longest
 * non decreasing subsequence. The later counts integers that are equal.
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/solution/#
 */
public class LongestNonDecreasingSubsequence {

  // length of longest nondecreasing subsequence recursive
  public int llndsRec(int[] arr) {
    return llndsRec(arr, Integer.MIN_VALUE, 0);
  }

  private int llndsRec(int[] arr, int prev, int pos) {
    if (pos == arr.length) {
      return 0;
    }
    int taken = 0;
    if (prev <= arr[pos]) {
      taken = 1 + llndsRec(arr, arr[pos], pos + 1);
    }
    int notTaken = llndsRec(arr, prev, pos + 1);
    return Math.max(taken, notTaken);
  }

  public int llndsMemo(int[] arr) {
    int[][] memo = new int[arr.length + 1][arr.length];
    for (int[] a : memo) {
      Arrays.fill(a, -1);
    }
    return llndsMemo(arr, -1, 0, memo);
  }

  private int llndsMemo(int[] arr, int prevIndex, int pos, int[][] memo) {
    if (pos == arr.length) {
      return 0;
    }
    if (memo[prevIndex + 1][pos] >= 0) {
      return memo[prevIndex + 1][pos];
    }
    int taken = 0;
    if (prevIndex < 0 || arr[prevIndex] <= arr[pos]) {
      taken = 1 + llndsMemo(arr, pos, pos + 1, memo);
    }
    int notTaken = llndsMemo(arr, prevIndex, pos + 1, memo);
    memo[prevIndex + 1][pos] =  Math.max(taken, notTaken);
    return memo[prevIndex + 1][pos];
  }


  /**
   * Time complexity : O(n^2). Two loops of nnn are there.
   * Space complexity : O(n). dp array of size n is used.
   * @param arr
   * @return
   */
  public int llnds(int[] arr) {
    if (arr.length == 0) {
      return 0;
    }
    int[] lis = new int[arr.length];
    Arrays.fill(lis, 1);

    int max = 0;
    for (int i = 1; i < arr.length; i++) {
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
    System.out.println(new LongestNonDecreasingSubsequence().llndsRec(new int[]{10, 22, 22, 22, 9, 33, 21, 50, 41, 60}));
    System.out.println(new LongestNonDecreasingSubsequence().llndsMemo(new int[]{10, 22, 22, 22, 9, 33, 21, 50, 41, 60}));
    System.out.println(new LongestNonDecreasingSubsequence().llnds(new int[]{10, 22, 22, 22, 9, 33, 21, 50, 41, 60}));
  }
}
