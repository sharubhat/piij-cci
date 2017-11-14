package com.leetcode.ds.easy;

/**
 * https://leetcode.com/problems/paint-house/description/
 * Let us assume 0 is red, 1 is blue, and 2 is green.
 * If 'i'th house needs to be painted red, the previous house should have been painted blue or green.
 * Similarly if 'i'th house needs to be painted blue, the previous house should be painted red or green.
 * Keeping the cost minimum, total cost of painting 'i' houses is cost of painting 'i'th house with a
 * particular color and minimum cost of painting the previous house with one of the other two. Once we
 * have the cost for each color at 'i'th house, we can choose the minimum of all three to keep the
 * total cost minimum.
 */
public class P256PaintHouse {
  public int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }
    for (int i = 1; i < costs.length; i++) {
      costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
      costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
      costs[i][2] += Math.min(costs[i - 1][2], costs[i - 1][0]);
    }
    int n = costs.length;
    return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
  }
}
