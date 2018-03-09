package leetcode.medium.dp;

/**
 * https://leetcode.com/problems/house-robber-ii/description/
 * Approach:
 * Same as {@link leetcode.easy.dp.P198HouseRobber} but since there is an extra condition that
 * the list is circular, we need to be a little careful. A simple way to break the circle is to either
 * leave out first element or leave out the last as they can't be robbed together. Then we can re-use
 * the modified {@link leetcode.easy.dp.P198HouseRobber} approach.
 */
public class P213HouseRobberII {
  private int rob(int[] nums, int start, int end) {
    int prevSum = 0;
    int currSum = 0;
    for (int i = start; i <= end; i++) {
      int tmp = currSum;
      currSum = Math.max(prevSum + nums[i], currSum);
      prevSum = tmp;
    }
    return currSum;
  }

  public int rob(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    int includeFirst = rob(nums, 0, nums.length - 2);
    int excludeFirst = rob(nums, 1, nums.length - 1);
    return Math.max(includeFirst, excludeFirst);
  }

  public static void main(String[] args) {
    System.out.println(new P213HouseRobberII().rob(new int[]{1,1}));
  }
}
