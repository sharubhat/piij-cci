package leetcode.arraysandstrings;

/**
 * https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
 */
public class PivotIndex {
  public int pivotIndex(int[] nums) {
    int sum = sumAll(nums);
    int leftSum = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i != 0) {
        leftSum += nums[i - 1];
      }
      int rightSum = sum - leftSum - nums[i];
      if (leftSum == rightSum) {
        return i;
      }
    }
    return -1;
  }

  private int sumAll(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    return sum;
  }
}
