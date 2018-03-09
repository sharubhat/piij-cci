package leetcode.medium;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-genetic-mutation/description/
 */
public class P433MinimumGeneticMutation {
  public int minMutation(String start, String end, String[] bank) {
    if (start.equals(end)) {
      return 0;
    }
    Set<String> bankSet = new HashSet(Arrays.asList(bank));
    char[] charSet = new char[]{'A', 'C', 'G', 'T'};

    int level = 0;
    Queue<String> queue = new LinkedList();
    Set<String> visited = new HashSet<>();
    queue.offer(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        String curr = queue.poll();
        if (curr.equals(end)) {
          return level;
        }
        char[] currArray = curr.toCharArray();
        for (int c= 0; c< currArray.length; c++) {
          char old = currArray[c];
          for (int i = 0; i < charSet.length; i++) {
            currArray[c] = charSet[i];
            String newString = new String(currArray);
            if (bankSet.contains(newString) && !visited.contains(newString)) {
              visited.add(newString);
              queue.offer(newString);
            }
          }
          currArray[c] = old;
        }
      }
      level++;
    }
    return -1;
  }
}
