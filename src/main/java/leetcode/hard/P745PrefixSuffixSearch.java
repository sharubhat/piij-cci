package leetcode.hard;

import java.util.*;

/**
 * https://leetcode.com/contest/weekly-contest-by-app-academy/problems/prefix-and-suffix-search/
 * Leetcode returns runtime error although this algorithm works.
 */
public class P745PrefixSuffixSearch {
  Map<String, List<Integer>> pMap = new HashMap<>();
  Map<String, List<Integer>> sMap = new HashMap<>();

  public P745PrefixSuffixSearch(String[] words) {
    for(int w = 0; w < words.length; w++) {
      String word = words[w];
      for (int i = 0; i <= word.length(); i++) {
        String prefix = word.substring(0, i);
        if (!pMap.containsKey(prefix)) {
          pMap.put(prefix, new ArrayList<>());
        }
        pMap.get(prefix).add(w);

        String suffix = word.substring(word.length() - i);
        if (!sMap.containsKey(suffix)) {
          sMap.put(suffix, new ArrayList<>());
        }
        sMap.get(suffix).add(w);
      }
    }
  }

  public int f(String prefix, String suffix) {
    if (!pMap.containsKey(prefix) || !sMap.containsKey(suffix)) {
      return -1;
    }
    List<Integer> a = pMap.get(prefix);
    List<Integer> b = sMap.get(suffix);
    if (a.size() > b.size()) {
      List<Integer> t = a;
      a = b;
      b = t;
    }
    for (int i = a.size() - 1; i >=0; i++) {
      if (b.contains(a.get(i))) {
        return a.get(i);
      }
    }
    return -1;
  }

  public static void main(String[] args) {
//    P745PrefixSuffixSearch wordFilter = new P745PrefixSuffixSearch(new String[]{"apple", "aaee", "monkey", "appple"});
//    System.out.println(wordFilter.f("a", "e"));
//    System.out.println(wordFilter.f("b", ""));

    P745PrefixSuffixSearch wf = new P745PrefixSuffixSearch(new String[]{"pop"});

    String[][] input = new String[][]{{"",""},{"","p"},{"","op"},{"","pop"},{"p",""},{"p","p"},{"p","op"},{"p","pop"},{"po",""},{"po","p"},{"po","op"},{"po","pop"},{"pop",""},{"pop","p"},{"pop","op"},{"pop","pop"},{"",""},{"","p"},{"","gp"},{"","pgp"},{"p",""},{"p","p"},{"p","gp"},{"p","pgp"},{"pg",""},{"pg","p"},{"pg","gp"},{"pg","pgp"},{"pgp",""},{"pgp","p"},{"pgp","gp"},{"pgp","pgp"}};
    for (String[] sarr : input) {
      System.out.println(wf.f(sarr[0], sarr[1]));
    }
  }
}
