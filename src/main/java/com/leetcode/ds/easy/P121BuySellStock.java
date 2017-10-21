package com.leetcode.ds.easy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 */
public class P121BuySellStock {
  public int maxProfit(int[] prices) {
    int max = 0;
    int profit = 0;
    for (int i = prices.length - 1; i > 0; i--) {
      int curr = prices[i];
      int prev = prices[i - 1];
      max = Math.max(curr, max);
      if(max > prev) {
        profit = Math.max(profit, max - prev);
      }
    }
    return profit;
  }
}
