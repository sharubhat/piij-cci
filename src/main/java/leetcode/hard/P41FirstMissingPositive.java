package leetcode.hard;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/first-missing-positive/description/
 */
public class P41FirstMissingPositive {
  public int firstMissingPositive(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
        swap(nums, i, nums[i] - 1);
      }
    }
    System.out.println(Arrays.toString(nums));
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public static void main(String[] args) {
    System.out.println(new P41FirstMissingPositive().firstMissingPositive(new int[] {3,4,-1,1}));
  }
}
