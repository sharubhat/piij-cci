package com.leetcode.ds.easy.greedy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * Greedy approach and not dp.
 * Every time we hit a new lowest number, min gets updated. However, profit is calculated as
 * difference between min so far and max so far. You might encounter a new min later which might not
 * contribute to max profit.
 * E.g: [8, 2, 4, 9, 1, 7]
 * At first 8 is chosen as minimum but a new minimum of 2 shows that stock price is falling and so
 * it's better to wait till it hits rock bottom. However we soon see the price raising and we start
 * calculating the profit with respect to min and keep updating it until the price keeps raising. If we
 * encounter a new min, in this example 1, we update min. However as we progress, we see that any
 * new profit that can be made by buying stock at 1 will not beat the profit we already made. Thus
 * greedy approach works correctly in this problem.
 */
public class P121BuySellStock {
  public int maxProfit(int[] prices) {
    int min = Integer.MAX_VALUE;
    int profit = 0;
    for (int i = 0; i < prices.length; i++) {
      if (min > prices[i]) {
        min = prices[i];
      } else if (profit < prices[i] - min) {
        profit = prices[i] - min;
      }
    }
    return profit;
  }
}
