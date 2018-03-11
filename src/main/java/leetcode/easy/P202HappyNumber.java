package leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/description/
 */
public class P202HappyNumber {
  public boolean isHappy(int n) {
    Set<Integer> seen = new HashSet<>();
    seen.add(n);

    int[] squares = new int[10];
    for (int i = 0 ; i < 10; i++) {
      squares[i] = i * i;
    }

    while (n != 1) {
      n = happySum(n, squares);
      if (seen.contains(n)) {
        return false;
      }
      seen.add(n);
    }
    return true;
  }

  private int happySum(int n, int[] squares) {
    int sum = 0;
    while (n > 0) {
      sum += squares[n % 10];
      n = n / 10;
    }
    return sum;
  }
}
