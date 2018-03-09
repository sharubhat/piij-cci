package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/description/
 * Ref: https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
 */
public class P325MaximumSizeSubarraySumK {
  public int maxSubArrayLen(int[] nums, int k) {
    int sum = 0;
    int maxLen = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      if (sum == k) {
        maxLen = i + 1;
      } else if (map.containsKey(sum - k)) {
        maxLen = Math.max(maxLen, i - map.get(sum - k));
      }

      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }
    }

    return maxLen;
  }
}
