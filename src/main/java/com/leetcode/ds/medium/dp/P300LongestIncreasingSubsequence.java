package com.leetcode.ds.medium.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
/* Approach :
 *
 * Optimal Substructure:
 * LIS(i) -> Length of longest increasing subsequence which includes A[i]as it's last
 * element.
 * LIS(i) = 1 + Max(LIS(j) where j < i such that A[j] < A[i])
 * LIS(i) = 1 if no such j exists
 *
 * Overlapping Subproblems:
 * If we solve using recursion for calculating the solution for jth index, we will be
 * solving the subproblems again which we had solved earlier while solving the
 * solution for (j-1)th index.
 *
 * Example:
 * A[] = {2,4,1,6}
 * i = 1, LIS(1) = 1
 * i = 2, LIS(2) = 1 + Max(LIS(1)) = 1 + 1 = 2 (4 > 2)
 * i = 3, LIS(3) = 1 (1 < 3, 1 < 4)
 * i = 4, LIS(4) = 1 + Max(LIS(1), LIS(2), LIS(3)) = 1 + 2 = 3 (6 > 2, 6 > 4, 6 > 1)
 *
 * NOTE: To print the Actual elements -
 * find the index which contains the longest sequence, print that index from main array.
 * Start moving backwards and pick all the indexes which are in sequence (descending).
 * If longest sequence for more than one indexes, pick any one.
 *
 * O(N^2) time complexity, O(N) space complexity
 */
public class P300LongestIncreasingSubsequence {
  public void findSubSequence(int[] arr) {
    int[] lis = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      int max = -1;
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i]) {
          if (max == -1 || max < lis[j] + 1) {
            max = 1 + lis[j];
          }
        }
      }
      lis[i] = (max == -1) ? 1 : max; // (max == -1) means none of arr[j] < arr[i]
    }
    // find max in the lis[]
    int result  = -1;
    int index = -1;
    for (int i = 0; i < arr.length; i++) {
      if (result < lis[i]) {
        result = lis[i];
        index = i;
      }
    }
    // start moving backwards from the end
    int res = result;
    int[] subseq = new int[res];
    for (int i = index; i >= 0; i--) {
      if (lis[i] == res) {
        subseq[--res] = arr[i];
      }
    }

    System.out.println(result);
    System.out.println(Arrays.toString(subseq));
  }

  public static void main(String[] args) {
    int[] a = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
    P300LongestIncreasingSubsequence i = new P300LongestIncreasingSubsequence();
    i.findSubSequence(a);
  }
}
