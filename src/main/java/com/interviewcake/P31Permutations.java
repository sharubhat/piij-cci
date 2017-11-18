package com.interviewcake;

import java.util.HashSet;
import java.util.Set;

/**
 * Recursive String permutations.
 * https://www.interviewcake.com/question/java/recursive-string-permutations
 * Generates n! permutations for strings with no repeated character.
 * Height of the recursion tree is n. So, time complexity is O(2^n).
 */
public class P31Permutations {
  public Set<String> generatePermutations(String s) {
    return permutationsHelper("", s, new HashSet<>());
  }

  private Set<String> permutationsHelper(String prefix, String s, Set<String> permutations) {
    if (s == null) {
      return permutations;
    }
    if (s.isEmpty()) {
      permutations.add(prefix);
    } else {
      int n = s.length();
      for (int i = 0; i < n; i++) {
        permutationsHelper(prefix + s.charAt(i),
            s.substring(0, i) + s.substring(i + 1, n),
            permutations);
      }
    }
    return permutations;
  }

  public static void main(String[] args) {
    System.out.println(new P31Permutations().generatePermutations("cat"));
  }
}
