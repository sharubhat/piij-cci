package leetcode.medium;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum/description/
 *
 * Two ways to solve.
 * 1. Pick a number and solve the given array for two sum with target as -number.
 * 2. Solve the problem similar to https://leetcode.com/problems/container-with-most-water/description/. Below solution is similar to that.
 */
public class P15ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length == 0) {
      return Collections.emptyList();
    }

    Set<List<Integer>> res = new HashSet<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
          res.add(Arrays.asList(nums[i], nums[j], nums[k]));
        } else if (sum < 0) {
          j++;
        } else  {
          k++;
        }
      }
    }
    return new ArrayList<>(res);
  }
}
