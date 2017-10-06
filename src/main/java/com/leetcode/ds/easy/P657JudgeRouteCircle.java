package com.leetcode.ds.easy;

/**
 * https://leetcode.com/problems/judge-route-circle/description/
 */
public class P657JudgeRouteCircle {
  /**
   * Straight forward implementation.
   * @param moves input string
   * @return boolean
   */
  public boolean judgeCircle(String moves) {
    int vmove = 0;
    int hmove = 0;
    for (char c : moves.toCharArray()) {
      if (c == 'U') {
        vmove++;
      } else if (c == 'D') {
        vmove--;
      } else if (c == 'L') {
        hmove++;
      } else {
        hmove--;
      }
    }
    return vmove == hmove;
  }

  /**
   * This code is cleaner and shorter. While other implementation might seem better
   * because it uses lesser memory, space complexity of both implementations are constant.
   * @param moves input string
   * @return boolean
   */
  public boolean judgeCircleLessCode(String moves) {
    int[] counts = new int[128];
    for (char c : moves.toCharArray()) {
      counts[c]++;
    }
    return counts['U'] == counts['D'] && counts['L'] == counts['R'];
  }
}
