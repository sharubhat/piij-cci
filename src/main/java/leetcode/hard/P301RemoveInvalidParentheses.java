package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 */
public class P301RemoveInvalidParentheses {
  private void remove(String s, List<String> res, int lastI, int lastJ, char[] par) {
    for (int stack = 0, i = lastI; i < s.length(); i++) {
      if (s.charAt(i) == par[0]) {
        stack++;
      } else if (s.charAt(i) == par[1]) {
        stack--;
      }
      if (stack < 0) {
        for (int j = lastJ; j <= i; j++) {
          if (s.charAt(j) == par[1] && (j == lastJ || s.charAt(j - 1) != par[1]))
            remove(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, par);
        }
        return;
      }
    }
    String reversed = new StringBuilder(s).reverse().toString();
    if (par[0] == '(') {
      remove(reversed, res, 0, 0, new char[]{')', '('});
    } else {
      res.add(reversed);
    }
  }

  public List<String> removeInvalidParenthesesNoDupe(String s) {
    List<String> res = new ArrayList<>();
    remove(s, res, 0, 0, new char[]{'(', ')'});
    return res;
  }

  // A more readable less optimal implementation

  // this algorithm will do brute force
  // DFS search, 2^n choice.
  // argument: matchChecking
  // make sure that the parathese is correct to match
  // "()",
  // if "(" is used, then matchChecking should increment one first
  // and then visit ")",
  // if ")" is used, then decrement one and then is equal to zero.
  // ")(",
  // if ")" is used, then matchChecking decrease one for ")", < 0, return false
  public List<String> removeInvalidParentheses(String s) {
    int[] invalid = countInvalidParentheses(s);

    int maxRemovalLeft = invalid[0];
    int maxRemovalRight = invalid[1];

    Set<String> deDuplicate = new HashSet<>();
    findAllValid(s, 0, deDuplicate, new StringBuilder(),
            maxRemovalLeft, maxRemovalRight, 0);

    return new ArrayList<>(deDuplicate);
  }

  private static void findAllValid(String s, int index, Set<String> deDuplicate,
                                   StringBuilder candidate, int maxRemovalLeft,
                                   int maxRemovalRight, int matchChecking) {
    if (maxRemovalLeft < 0 || maxRemovalRight < 0 || matchChecking < 0) {
      return;
    }
    int length = s.length();
    boolean finishScan = index == length;
    if (finishScan) {
      if (maxRemovalLeft == 0 &&
              maxRemovalRight == 0 &&
              matchChecking == 0) {
        deDuplicate.add(candidate.toString());
      }
      return;
    }

    char visit = s.charAt(index);
    int current = candidate.length();
    boolean isOpen = visit == '(';
    boolean isClose = visit == ')';

    if (isOpen) {
      // don't use '(', remove '('
      findAllValid(s, index + 1, deDuplicate, candidate,
              maxRemovalLeft - 1, maxRemovalRight, matchChecking);
      findAllValid(s, index + 1, deDuplicate, candidate.append(visit),
              maxRemovalLeft, maxRemovalRight, matchChecking + 1);
    } else if (isClose) {
      // don't use ')', remove ')'
      findAllValid(s, index + 1, deDuplicate, candidate,
              maxRemovalLeft, maxRemovalRight - 1, matchChecking);
      findAllValid(s, index + 1, deDuplicate, candidate.append(visit),
              maxRemovalLeft, maxRemovalRight, matchChecking - 1);
    } else {
      findAllValid(s, index + 1, deDuplicate, candidate.append(visit),
              maxRemovalLeft, maxRemovalRight, matchChecking);
    }
    // backtracking - remove the last character
    candidate.setLength(current);
  }

  private static int[] countInvalidParentheses(String s) {
    int maxRemovalLeft = 0;
    int maxRemovalRight = 0;
    int length = s.length();
    for (int i = 0; i < length; i++) {
      char visit = s.charAt(i);
      boolean isOpen = visit == '(';
      boolean isClose = visit == ')';

      if (isOpen) {
        maxRemovalLeft++;
      } else if (isClose) {
        if (maxRemovalLeft != 0) {
          maxRemovalLeft--;
        } else {
          maxRemovalRight++;
        }
      }
    }
    return new int[]{maxRemovalLeft, maxRemovalRight};
  }

  public static void main(String[] args) {
    System.out.println(new P301RemoveInvalidParentheses().removeInvalidParentheses("()())()"));
  }
}
