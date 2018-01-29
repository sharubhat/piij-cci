package com.leetcode.ds.easy;

import java.util.Stack;

public class P20ValidParentheses {
  public boolean isValid(String s) {
    if (s.length() % 2 != 0) {
      return false;
    }
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        stack.push(')');
      } else if (c == '{') {
        stack.push('}');
      } else if (c == '[') {
        stack.push(']');
      } else {
        if (stack.isEmpty() || stack.pop() != c) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(new P20ValidParentheses().isValid("()"));
  }
}
