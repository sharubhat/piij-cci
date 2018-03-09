package leetcode.hard.greedy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 */
public class P188BuySellStockIV {
  public int maxProfit(int k, int[] prices) {
    if(prices.length < 2)  return 0;
    int[] profit = new int[prices.length];
    // the max transactions with no overlap is prices.length / 2.
    if (k >= prices.length / 2) {
      k = prices.length / 2;
    }
    for(int trans=0; trans<k; trans++) {
      int maxBuy = profit[0] - prices[0];
      for(int i=1; i<prices.length; i++) {
        int tmp = profit[i];
        profit[i] = Math.max(profit[i-1], maxBuy + prices[i]);
        maxBuy = Math.max(maxBuy, tmp - prices[i]);
      }
    }
    return profit[prices.length - 1];
  }
}
