package datastructures.suffixarrays;

public class LongestCommonPrefix {
  private static int lcp(String s, String t) {
    int len = Math.min(s.length(), t.length());
    for (int i = 0; i < len; i++) {
      if (s.charAt(i) != t.charAt(i)) {
        return i;
      }
    }
    return len;
  }
}
