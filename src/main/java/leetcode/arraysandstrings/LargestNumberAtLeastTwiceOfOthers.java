package leetcode.arraysandstrings;

/**
 * https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1147/
 */
public class LargestNumberAtLeastTwiceOfOthers {
  public int dominantIndex(int[] nums) {
    int indexOfLargest = largestElIndex(nums);
    int largest = nums[indexOfLargest];

    for (int i = 0; i < nums.length; i++) {
      if (i != indexOfLargest) {
        if (nums[i] * 2 > largest) {
          return -1;
        }
      }
    }
    return indexOfLargest;
  }

  private int largestElIndex(int[] nums) {
    int index = -1;
    int largest = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > largest) {
        largest = nums[i];
        index = i;
      }
    }
    return index;
  }
}
