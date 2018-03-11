package leetcode.easy;

/**
 * https://leetcode.com/problems/power-of-three/description/
 * Logic :
 * n = 3 ^ x
 * x = log3(n) = log10(n) / log10(3). If n is perfect cube of some number x, then x should be a
 * whole number. Checking if a double is a whole number is, it should remain same when down casted.
 */
public class P326PowerOfThree {
  public boolean isPowerOfThree(int n) {
    double x = Math.log10(n) / Math.log10(3);
    return x == (int)x;
  }
}
