package com.leetcode.ds.easy;

import java.util.Stack;

/**
 * https://leetcode.com/problems/baseball-game/description/
 */
public class P682BaseballGame {
  /**
   * Returns the final score of Baseball Game.
   * TODO: There is scope for improvement. Resolve.
   * @param ops options
   * @return int
   */
  public int calPoints(String[] ops) {
    int result = 0;
    Stack<Integer> stack = new Stack<>();
    for (String s : ops) {
      if (s.equals("C")) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else if (s.equals("D")) {
        stack.push(stack.peek() * 2);
      } else if (s.equals("+")) {
        int p1 = stack.isEmpty() ? 0 : stack.pop();
        int p2 = stack.isEmpty() ? 0 : stack.peek();
        stack.push(p1);
        stack.push(p1 + p2);
      } else {
        stack.push(Integer.parseInt(s));
      }
    }
    while (!stack.isEmpty()) {
      result += stack.pop();
    }
    return result;
  }
}
