package topcoder.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of N coins, their values (V1, V2, … , VN), and the total sum S, find the minimum
 * number of coins the sum of which is S (we can use as many coins of one type as we want), or
 * report that it’s not possible to select coins in such a way that they sum up to S.
 *
 * Ref: https://www.topcoder.com/community/data-science/data-science-tutorials/dynamic-programming-from-novice-to-advanced/
 * Ref: https://www.linkedin.com/pulse/dynamic-programming-part-2-amritpal-singh
 *
 * Note: Solution here has a little more conditions than the ones found on internet.
 * This is because the solutions available online do not return correct values when sum can't be formed.
 */
public class MinNumCoins {

  /**
   * Recursive solution
   *
   * @param coins
   * @param sum
   * @return
   */
  public int minCoinsRec(int[] coins, int sum) {
    int min = minCoinsRecHelper(coins, sum);
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private int minCoinsRecHelper(int[] coins, int sum) {
    if (sum == 0) {
      return 0;
    }

    int numCoins;
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < coins.length; i++) {
      if (sum >= coins[i]) {
        int minCoins = minCoinsRecHelper(coins, sum - coins[i]);
        // if recursion tree returns infinity, we ignore that subtree as it doesn't yield a
        // solution.
        if (minCoins != Integer.MAX_VALUE) {
          numCoins = 1 + Math.min(min, minCoins);
          min = Math.min(min, numCoins);
        }
      }
    }
    return min;
  }

  /**
   * Top down dp solution
   *
   * @param coins
   * @param sum
   * @return
   */
  public int minCoinsTD(int[] coins, int sum) {
    int min = minCoinsTD(coins, sum, new HashMap<>());
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private int minCoinsTD(int[] coins, int sum, Map<Integer, Integer> memo) {
    if (sum == 0) {
      return 0;
    }

    if (memo.containsKey(sum)) {
      return memo.get(sum);
    }

    int numCoins;
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < coins.length; i++) {
      if (sum >= coins[i]) {
        int minCoins = minCoinsRecHelper(coins, sum - coins[i]);
        // if recursion tree returns infinity, we ignore that subtree as it doesn't yield a
        // solution.
        if (minCoins != Integer.MAX_VALUE) {
          numCoins = 1 + Math.min(min, minCoins);
          min = Math.min(min, numCoins);
        }
      }
    }
    memo.put(sum, min);
    return min;
  }

  /**
   * Bottom up solution
   *
   * @param coinValues
   * @param sum
   * @return
   */
  public int minCoinsBU(int[] coinValues, int sum) {
    int[] sums = new int[sum + 1];
    Arrays.fill(sums, Integer.MAX_VALUE);
    sums[0] = 0;

    for (int currSum = 0; currSum < sums.length; currSum++) {
      for (int currCoin : coinValues) {
        if (currCoin <= currSum) {
          int previousSum = sums[currSum - currCoin];
          // previousSum != Integer.MAX_VALUE is important
          // if previousSum has no solution, current sum also can't have a solution.
          if (previousSum != Integer.MAX_VALUE && previousSum + 1 < sums[currSum]) {
            sums[currSum] = previousSum + 1;
          }
        }
      }
    }
    return sums[sum] == Integer.MAX_VALUE ? -1 : sums[sum];
  }

  public static void main(String[] args) {
    System.out.println(new MinNumCoins().minCoinsRec(new int[] {3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsRec(new int[] {1, 3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsRec(new int[] {3, 5}, 7));
    System.out.println(new MinNumCoins().minCoinsRec(new int[] {3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsRec(new int[] {4, 5, 6, 9}, 12));
    System.out.println(new MinNumCoins().minCoinsRec(new int[] {6, 9, 5}, 4));
    System.out.println(new MinNumCoins().minCoinsRec(new int[] {6, 9, 5}, 46));
    System.out.println();
    System.out.println(new MinNumCoins().minCoinsTD(new int[] {3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsTD(new int[] {1, 3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsTD(new int[] {3, 5}, 7));
    System.out.println(new MinNumCoins().minCoinsTD(new int[] {4, 5, 6, 9}, 12));
    System.out.println(new MinNumCoins().minCoinsTD(new int[] {3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsTD(new int[] {6, 9, 5}, 4));
    System.out.println(new MinNumCoins().minCoinsTD(new int[] {6, 9, 5}, 46));
    System.out.println();
    System.out.println(new MinNumCoins().minCoinsBU(new int[] {3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsBU(new int[] {1, 3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsBU(new int[] {3, 5}, 7));
    System.out.println(new MinNumCoins().minCoinsBU(new int[] {4, 5, 6, 9}, 12));
    System.out.println(new MinNumCoins().minCoinsBU(new int[] {3, 5}, 10));
    System.out.println(new MinNumCoins().minCoinsBU(new int[] {6, 9, 5}, 4));
    System.out.println(new MinNumCoins().minCoinsBU(new int[] {6, 9, 5}, 46));
  }
}
