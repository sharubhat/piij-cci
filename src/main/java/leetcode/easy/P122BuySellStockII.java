package leetcode.easy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 * Example:
 * [20, 10, 8, 22, 30, 2, 40, 41, 1]
 *                             41
 *                         40
 *                 30
 *             22
 * 20
 *     10
 *         8
 *                     2
 *                                 1
 * Buying at 20 and selling at 10 -> profit = -10.
 * Buying at 20 and selling at 8 -> profit = -12 which is also same as buying at 20, selling at 10,
 * buying at 10 and selling at 8.
 * Also we notice here that if the stock price is falling, we don't want to trade as if are loosing.
 * <p/>
 * Two things are clear from above transaction.
 * 1. All we need is profit made at i-2 and profit made by buying at i-1 and selling at i.
 * 2. If we are loosing by buying at i-1 and selling at i, we don't count it.
 * <p/>
 * Proceeding with example, buy at 8, sell at 22 -> profit = 14.
 * Buy at 22, sell at 30 -> profit = 8. Total profit so far = 22,
 * same as buy at 8 and sell at 30... and so on.
 */
public class P122BuySellStockII {
  public int maxProfit(int[] prices) {
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        profit += prices[i] - prices[i - 1];
      }
    }
    return profit;
  }
}
