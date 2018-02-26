package com.leetcode.hard.greedy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 */
public class P123BuySellStockIII {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }

    int n = prices.length;
    int profit = 0;

    int[] profitFromLeft = new int[n];
    int min = prices[0];

    for (int i = 1; i < n; i++) {
      profitFromLeft[i] = Math.max(profitFromLeft[i - 1], prices[i] - min);
      min = Math.min(prices[i], min);
    }

    int[] profitFromRight = new int[n];
    int max = prices[n - 1];

    for (int i = n - 2; i >= 0; i--) {
      profitFromRight[i] = Math.max(profitFromRight[i + 1], max - prices[i]);
      max = Math.max(max, prices[i]);
      profit = Math.max(profit, profitFromRight[i] + profitFromLeft[i]);
    }
    return profit;
  }

  // alternative solution that generalizes number of trades
  public int maxProfit2(int[] prices) {
    if(prices.length < 2)  return 0;
    int[] profit = new int[prices.length];
    for(int k=0; k<2; k++) {
      int maxBuy = profit[0] - prices[0];
      for(int i=1; i<prices.length; i++) {
        int tmp = profit[i];
        profit[i] = Math.max(profit[i-1], maxBuy + prices[i]);
        maxBuy = Math.max(maxBuy, tmp - prices[i]);
      }
    }
    return profit[prices.length - 1];
  }

  public static void main(String[] args) {
    System.out.println(new P123BuySellStockIII().maxProfit(new int[]{1,4,2}));
  }
}
