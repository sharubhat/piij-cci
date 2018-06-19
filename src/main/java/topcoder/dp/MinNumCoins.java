package topcoder.dp;

import java.util.Arrays;

/**
 * Given a list of N coins, their values (V1, V2, … , VN), and the total sum S,
 * find the minimum number of coins the sum of which is S
 * (we can use as many coins of one type as we want),
 * or report that it’s not possible to select coins in such a way that they sum up to S.
 */
public class MinNumCoins {
  public int minCoinsRequired(int[] values, int sum) {
    int[] min = new int[sum + 1];
    Arrays.fill(min, Integer.MAX_VALUE);
    min[0] = 0;

    for (int i = 0; i < min.length; i++) {
      for (int j = 0; j < values.length; j++) {
        if (values[j] <= i && min[i - values[j]] + 1 < min[i]) {
          min[i] = min[i - values[j]] + 1;
        }
      }
    }
    return min[sum];
  }

  public static void main(String[] args) {
    System.out.println(new MinNumCoins().minCoinsRequired(new int[] {1, 3, 5}, 10));
  }
}
