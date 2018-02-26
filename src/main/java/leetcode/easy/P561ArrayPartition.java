package com.leetcode.easy;

/**
 * https://leetcode.com/problems/array-partition-i/description/
 */
public class P561ArrayPartition {
  /**
   * While Arrays.sort() and summing up alternative numbers starting from 0th index might
   * look like a straight forward solution, we need to keep in mind that sort() is O(NlogN)
   * time complexity. Taking a closer look at problem constraints tells us that the problem
   * can be solved differently using an additional constant space but with two pass of the array.
   * @param nums input array
   * @return int
   */
  public int arrayPairSum(int[] nums) {
    int[] buffer = new int[2 * 10000 + 1];
    for (int i = 0; i < nums.length; i++) {
      buffer[nums[i] + 10000]++;
    }
    int sum = 0;
    boolean isOdd = true;
    for (int i = 0; i < buffer.length; i++) {
      while (buffer[i] > 0) {
        if (isOdd) {
          sum += i - 10000;
          isOdd = false;
        } else {
          isOdd = true;
        }
        buffer[i]--;
      }
    }
    return sum;
  }
}
