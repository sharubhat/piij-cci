package com.leetcode.easy;

/**
 * https://leetcode.com/problems/hamming-distance/description/
 */
public class P461HammingDistance {
  /**
   * Return number of positions at which bits are different.
   * @param x first input
   * @param y second input
   * @return int
   */
  public int hammingDistance(int x, int y) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      if (((x >> i) & 1) != ((y >> i) & 1)) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    System.out.println(new P461HammingDistance().hammingDistance(3, 1));
  }
}
