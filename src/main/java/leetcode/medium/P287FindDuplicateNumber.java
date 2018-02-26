package com.leetcode.medium;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 *
 * Check http://keithschwarz.com/interesting/code/?dir=find-duplicate for explanation
 * https://en.wikipedia.org/wiki/Cycle_detection
 */
public class P287FindDuplicateNumber {
  public int findDuplicate(int[] nums) {
    int slow = 0;
    int fast = 0;
    int len = nums.length;
    while (fast < len && nums[fast] < len) {
      slow = nums[slow];
      fast = nums[nums[fast]];
      if(slow == fast) {
        slow = 0;
        while(slow != fast) {
          slow = nums[slow];
          fast = nums[fast];
        }
        return slow;
      }
    }
    return -1;
  }
}
