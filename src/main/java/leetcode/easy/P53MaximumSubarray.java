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
    int maxSoFar = nums[0];
    int maxEndingHere = nums[0];
    for (int i = 1; i < nums.length; i++) {
      maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
      maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    return maxSoFar;
  }
}
