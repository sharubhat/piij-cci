package com.leetcode.ds.medium.dp;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 */
public class P300Faster {
  private int ceil(int[] arr, int key, int left, int right) {
    int mid;
    while(right - left > 1) {
      mid = left + (right - left)/2;
      if(arr[mid] >= key) {
        right = mid;
      } else {
        left = mid;
      }
    }
    return right;
  }

  public int lengthOfLIS(int[] nums) {
    if(nums == null || nums.length == 0) {
      return 0;
    }
    int[] tail = new int[nums.length];
    tail[0] = nums[0];
    int len = 1;
    for (int i = 1; i < nums.length; i++) {
      if(nums[i] < tail[0]) { // new smallest value found
        tail[0] = nums[i];
      } else if(nums[i] > tail[len - 1]) { // nums[i] can extend largest value subsequence
        tail[len++] = nums[i];
      } else { // nums[i] wants to replace ceil value
        tail[ceil(tail, nums[i], -1, len - 1)] = nums[i];
      }
    }
    return len;
  }
}
