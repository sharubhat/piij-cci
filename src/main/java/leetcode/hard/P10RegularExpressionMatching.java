package leetcode.hard;

/**
 * Ref: https://leetcode.com/problems/regular-expression-matching/discuss/191335/A-straightforward-recursive-solution-in-Java
 *
 */
public class P10RegularExpressionMatching {
  public boolean isMatch(String s, String p) {
    // "", ""
    if (s.isEmpty() && p.isEmpty()) {
      return true;
    }
    // "a", ""
    if (!s.isEmpty() && p.isEmpty()) {
      return false;
    }
    // "aa", "a*"
    // "", "a*"
    if (p.length() >= 2 && p.charAt(1) == '*') {
      return matchStar(s, p);
    }
    // aa, a
    // aa, .
    return matchFirst(s, p) && isMatch(s.substring(1), p.substring(1));
  }

  boolean matchStar(String s, String p) {
    // recursion tree branches here. a* has zero match OR a* has one or more match
    return isMatch(s, p.substring(2)) ||
        (matchFirst(s, p) && isMatch(s.substring(1), p));
  }

  boolean matchFirst(String s, String p) {
    return !s.isEmpty() &&
        !p.isEmpty() &&
        (p.charAt(0) == '.' ||
            s.charAt(0) == p.charAt(0));
  }
}
