package com.leetcode.ds.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * https://leetcode.com/problems/two-sum/description/
 */
public class P1TwoSum {
  /**
   * brute force, O(n^2).
   * @param nums input array
   * @param target sum
   * @return Optional
   */
  public Optional<int[]> twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return Optional.of(new int[]{i, j});
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Faster approach trading space with time. O(n) space complexity, O(n) time complexity
   * @param nums input array
   * @param target sum
   * @return Optional
   */
  public Optional<int[]> twoSumFaster(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (!map.containsKey(nums[i])) {
        map.put(target - nums[i], i);
      } else {
        return Optional.of(new int[]{map.get(nums[i]), i});
      }
    }
    return Optional.empty();
  }
}
