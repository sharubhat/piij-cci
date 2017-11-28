package com.leetcode.ds.hard.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 * Keep in mind,
 * 1. subarray - array formed by contiguous indices.
 * 2. Problem states non-overlapping sub-array.
 */
public class P689MaxSum3SubArrays {
  public int[] maxSumOfThreeSubArrays(int[] nums, int K) {
    // w is array of sum of rolling windows
    int[] w = new int[nums.length - K + 1];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (i >= K) {
        sum -= nums[i - K];
      }
      if (i >= K - 1) {
        w[i - (K - 1)] = sum;
      }
    }
    System.out.println(Arrays.toString(w));

    int[] left = new int[w.length];
    int best = 0;
    for (int i = 0; i < w.length; i++) {
      if (w[i] > w[best]) {
        best = i;
      }
      left[i] = best;
    }
    System.out.println(Arrays.toString(left));

    int[] right = new int[w.length];
    best = w.length - 1;
    for (int i = w.length - 1; i >= 0; i--) {
      if (w[i] > w[best]) {
        best = i;
      }
      right[i] = best;
    }
    System.out.println(Arrays.toString(right));

    int[] ans = new int[]{-1, -1, -1};
    for (int j = K; j < w.length - K; j++) {
      int i = left[j - K];
      int k = right[j + K];
      if (ans[0] == -1 || w[i] + w[j] + w[k] > w[ans[0]] + w[ans[1]] + w[ans[2]]) {
        ans[0] = i;
        ans[1] = j;
        ans[2] = k;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new P689MaxSum3SubArrays().maxSumOfThreeSubArrays(new int[]{1,2,1,2,6,7,5,1}, 2)));
  }
}
