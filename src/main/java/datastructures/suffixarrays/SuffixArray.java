package datastructures.suffixarrays;

import java.util.Arrays;

// ref: Algorithms, 4th Edition by Robert Sedgewick
public class SuffixArray {
  private final String[] suffixes;
  private final int length;

  // build suffix array for given text
  public SuffixArray(String text) {
    this.length = text.length();
    suffixes = new String[length];
    for (int i = 0; i < length; i++) {
      suffixes[i] = text.substring(i);
    }
    Arrays.sort(suffixes);
  }

  // length of text
  int length() {
    return length;
  }

  // i'th in the suffix array
  String select(int i) {
    return suffixes[i];
  }

  // index of select(i)
  int index(int i) {
    return length - suffixes[i].length();
  }

  private static int lcp(String s, String t) {
    int len = Math.min(s.length(), t.length());
    for (int i = 0; i < len; i++) {
      if (s.charAt(i) != t.charAt(i)) {
        return i;
      }
    }
    return len;
  }

  // length of longest common prefix of select(i) and select(i - 1)
  int longestCommonPrefix(int i) {
    return lcp(suffixes[i], suffixes[i-1]);
  }

  // number of suffixes less than "suffix with key as prefix" OR
  // index of "suffix with key as prefix" in suffix array
  int rank(String key) {
    int low =0;
    int high = length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int cmp = key.compareTo(suffixes[mid]);
      if (cmp < 0) {
        high = mid - 1;
      } else if  (cmp > 0) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return low;
  }
}
