package leetcode.medium.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
/* Approach :
 *
 * Optimal Substructure:
 * UP(i) -> Length of longest increasing subsequence which includes A[i] as it's last
 * element.
 * UP(i) = 1 + Max(UP(j) where j < i such that A[j] < A[i])
 * UP(i) = 1 if no such j exists
 *
 * Overlapping Subproblems:
 * If we solve using recursion for calculating the solution for jth index, we will be
 * solving the subproblems again which we had solved earlier while solving the
 * solution for (j-1)th index.
 *
 * Example:
 * A[] = {2,4,1,6}
 * i = 1, UP(1) = 1
 * i = 2, UP(2) = 1 + Max(UP(1)) = 1 + 1 = 2 (4 > 2)
 * i = 3, UP(3) = 1 (1 < 3, 1 < 4)
 * i = 4, UP(4) = 1 + Max(UP(1), UP(2), UP(3)) = 1 + 2 = 3 (6 > 2, 6 > 4, 6 > 1)
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
    int[] up = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      int max = getMax(arr, up, i);
      if(max == -1) { // (max == -1) means none of arr[j] < arr[i]. So start new seq.
        max = 1;
      }
      up[i] = max;
    }
    // up[] or length of longest increasing subsequence at each index
    // in current example at this stage : [1, 2, 2, 1, 3, 3, 4, 4, 5, 6, 7, 2]

    // find max in the up[]
    int lengthLongest  = -1;
    int lastIndex = -1;
    for (int i = 0; i < arr.length; i++) {
      if (lengthLongest < up[i]) {
        lengthLongest = up[i];
        lastIndex = i;
      }
    }
    // start moving backwards from the end
    int res = lengthLongest;
    int[] subseq = new int[res];
    for (int i = lastIndex; i >= 0; i--) {
      // there can be more than one element which is capable of being part of
      // longest subsequence, however we just need one.
      // e.g 3,3,4,4 in current example up[]
      if (up[i] == res) {
        subseq[--res] = arr[i];
      }
    }

    System.out.println(lengthLongest);
    System.out.println(Arrays.toString(subseq));
  }

  private int getMax(int[] arr, int[] up, int i) {
    int max = -1;
    for (int j = 0; j < i; j++) {
      if (arr[j] < arr[i]) {
        if (max == -1 || max < up[j] + 1) {
          max = 1 + up[j];
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
    P300LongestIncreasingSubsequence i = new P300LongestIncreasingSubsequence();
    i.findSubSequence(a);
  }
}
