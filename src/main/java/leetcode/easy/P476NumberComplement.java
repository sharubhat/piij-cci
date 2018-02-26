package com.leetcode.easy;

/**
 * https://leetcode.com/problems/number-complement/description/
 */
public class P476NumberComplement {
  /**
   * May be not so good way because this approach is not pure bit manipulation and so involves
   * a lot more operations although the time complexity is O(n). May be I am wrong.
   * @param num input number
   * @return int
   */
  public int findComplement(int num) {
    int position = 0;
    int result = 0;
    while (num != 0) {
      if ((num & 1) == 0) {
        result += Math.pow(2, position);
      }
      num = num >> 1;
      position++;
    }
    return result;
  }

  /**
   * An alternative all bit twiddling way of solving the same problem.
   * @param num input number
   * @return int
   */
  public int findComplementBetter(int num) {
    int tmp = num;
    int count = 0;
    while (tmp > 0) {
      int mask = 1 << count;
      count++;
      num = num ^ mask;
      tmp = tmp >> 1;
    }
    return num;
  }

  public static void main(String[] args) {
    System.out.println(new P476NumberComplement().findComplementBetter(5));
  }
}
