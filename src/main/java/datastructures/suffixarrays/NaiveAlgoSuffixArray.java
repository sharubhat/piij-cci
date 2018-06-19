package datastructures.suffixarrays;

import java.util.*;

/**
 * On the naive approach, we are sorting N strings with an O(N log N) comparison based sorting algorithm.
 * As comparing strings takes O(N) time, we can conclude that the time complexity of our naive approach is O(N2 log N)
 */
public class NaiveAlgoSuffixArray {
  public static int[] suffixArray(String s) {
    Map<String, Integer> map = new HashMap<>();
    List<String> v = new ArrayList<>();

    for (int i = 0; i < s.length(); i++) {
      map.put(s.substring(i, s.length() - 1), i);
      v.add(s.substring(i, s.length() - 1));
    }

    Collections.sort(v);

    int[] suffixArray = new int[v.size()];

    for (int i = 0; i < v.size(); i++) {
      suffixArray[i] = map.get(v.get(i));
    }
    return suffixArray;
  }

  public static void main(String[] args) {
    String s = "attcatg$";
    System.out.println(Arrays.toString(suffixArray(s)));
  }
}
