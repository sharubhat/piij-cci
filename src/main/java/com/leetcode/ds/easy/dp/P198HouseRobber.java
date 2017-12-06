package com.leetcode.ds.easy.dp;

/**
 * https://leetcode.com/problems/house-robber/description/
 */
public class P198HouseRobber {
  /**
   * Obvious dp approach. Idea is, the maximum money robber can make when he is at last house
   * either includes or excludes the last house. If it includes, then it's sum of maximum money made
   * until last but two house plus last house money.
   * @param nums money stashed in each house
   * @return int
   */
  public int rob(int[] nums) {
    int dp[] = new int[nums.length + 1];
    return rob(nums, nums.length - 1, dp);
  }

  private int rob(int[] nums, int index, int[] dp) {
    if (index < 0) {
        return 0;
    }
    if (dp[index] == 0) {
        dp[index] = Math.max(rob(nums, index - 1, dp), nums[index] + rob(nums, index - 2, dp));
    }
    return dp[index];
  }

  /**
   * Memory optimized bottom up approach
   * @param nums money stashed in each house
   * @return int
   */
  public int rob2(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int prevSum = 0;
    int currSum = 0;
    for (int value : nums) {
      int tmp = currSum;
      currSum = Math.max(prevSum + value, currSum);
      prevSum = tmp;
    }
    return currSum;
  }
}
