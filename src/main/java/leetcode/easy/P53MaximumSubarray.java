package leetcode.easy;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 * Kaden's algorithm. The max sum is initially 0. Suppose we solved the problem for A[1 .. i - 1],
 * A[i] is either part of the sub-array that produces max sum or is beginning of another sub-array
 * that might be producing max sum. So we keep track of max sum we have found so far and max ending
 * at i.
 */
public class P53MaximumSubarray {
  public int maxSubArray(int[] nums) {
    int previousMax = nums[0];
    int maxEndingHere = nums[0];
    for (int i = 1; i < nums.length; i++) {
      maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
      previousMax = Math.max(previousMax, maxEndingHere);
    }
    // now previousMax is the max at last index of the array
    return previousMax;
  }

  // for easier understanding although it does the same thing as above with more space
  public int maxSubArray2(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    dp[0] = nums[0];
    int max = nums[0];

    for (int i = 1; i < n; i++) {
      dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
      max = Math.max(max, dp[i]);
    }
    return max;
  }
}
