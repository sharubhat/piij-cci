package com.leetcode.ds.medium.dp;

/**
 * https://leetcode.com/contest/weekly-contest-61/problems/delete-and-earn/
 * This question can be reduced to the House Robbers question also on LeetCode.
 * Observations:
 *  The order of nums does not matter.
 *  Once we decide that we want a num, we can add all the occurrences of num into the total.
 * We first transform the nums array into a points array that sums up the total number of points for
 * that particular value. A value of x will be assigned to index x in points.
 * nums: [2, 2, 3, 3, 3, 4] (2 appears 2 times, 3 appears 3 times, 4 appears once)
 * points: [0, 0, 4, 9, 4] <- This is the gold in each house!

 The condition that we cannot pick adjacent values is similar to the House Robber question that we cannot rob adjacent houses. Simply pass points into the rob function for a quick win 🌝!
 */
public class P740DeleteAndEarn {
  public int deleteAndEarn(int[] nums) {
    int[] points = new int[10001];
    for (int num : nums) {
      points[num] += num;
    }
    int prev = 0;
    int curr = 0;
    for (int value : points) {
      int tmp = curr;
      curr = Math.max(prev + value, curr);
      prev = tmp;
    }
    return curr;
  }

  public static void main(String[] args) {
    new P740DeleteAndEarn().deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4});
  }
}
