package com.leetcode.ds.medium;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 */
public class P209MinimumSizeSubarraySum {
  public int minSubArrayLen(int s, int[] nums) {
    int st = 0;
    int e = 0;
    int min = Integer.MAX_VALUE;
    int sum = 0;
    while (st <= e && e < nums.length) {
      sum += nums[e++];
      while (sum >= s) {
        min = Math.min(min, e - st);
        sum -= nums[st++];
      }
    }
    return min == Integer.MAX_VALUE? 0 : min;
  }

  public static void main(String[] args) {
    System.out.println(new P209MinimumSizeSubarraySum().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
  }
}
