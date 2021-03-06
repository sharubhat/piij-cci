package cake;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Recursive String permutations.
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

  List<String> anagrams(String input) {
    List<String> result = new ArrayList();
    backtrack("", input, result);
    return result;
  }

  void backtrack(String prefix, String input, List result) {
    if (prefix.length() == input.length()) {
      result.add(prefix);
    } else {
      for (char c : input.toCharArray()) {
        if (!contains(prefix, c)) {
          backtrack(prefix + c, input, result);
        }
      }
    }
  }

  boolean contains(String prefix, char c) {
    return prefix.indexOf(c) == -1;
  }

  public static void main(String[] args) {
    System.out.println(new P31Permutations().generatePermutations("cat"));
    System.out.println(new P31Permutations().anagrams("cat"));
  }
}
