package leetcode.easy.greedy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class P122BuySellStockII {
  public int maxProfit(int[] prices) {
    int min = Integer.MAX_VALUE;
    int profit = 0;
    int idx = 0;
    while (idx < prices.length) {
      while (idx < prices.length && prices[idx] < min) {
        min = prices[idx];
        idx++;
      }
      int max = min;
      while (idx < prices.length && max <= prices[idx]) {
        max = prices[idx];
        idx++;
      }
      profit += max - min;
      min = max;
    }

    return profit;
  }

  public int maxProfit2(int[] prices) {
    int maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1])
        maxProfit += prices[i] - prices[i - 1];
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.out.println(new P122BuySellStockII().maxProfit(new int[]{7, 1, 5,3, 6,4}));
  }
}
