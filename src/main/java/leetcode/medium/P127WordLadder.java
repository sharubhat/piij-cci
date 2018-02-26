package com.leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/description/
 */
public class P127WordLadder {
  /**
   * A bi-directional BFS which is faster than regular BFS.
   * This solution throws TimeLimitExceeded Exception for test cases on leetcode.
   * @param beginWord
   * @param endWord
   * @param wordList
   * @return
   */
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return 0;
    }
    int len = 1;
    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();
    Set<String> visited = new HashSet<>();
    beginSet.add(beginWord);
    endSet.add(endWord);
    visited.add(beginWord);
    visited.add(endWord);

    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
      // add new words to smaller set to achieve even distribution and better performance
      if (beginSet.size() > endSet.size()) {
        Set<String> tmp = beginSet;
        beginSet = endSet;
        endSet = tmp;
      }
      Set<String> next = new HashSet<>();
      len++;
      for (String word : beginSet) {
        for (int i = 0; i < word.length(); i++) {
          for (char c = 'a'; c <= 'z'; c++) {
            String newWord = word.substring(0, i) + c + word.substring(i + 1);
            if (endSet.contains(newWord)) {
              return len;
            }
            if (wordList.contains(newWord) && !visited.contains(newWord)) {
              visited.add(newWord);
              next.add(newWord);
            }
          }
        }
      }
      beginSet = next;
    }
    return 0;
  }
}
