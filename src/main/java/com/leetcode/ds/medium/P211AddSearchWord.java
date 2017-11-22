package com.leetcode.ds.medium;

/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/description/
 */
public class P211AddSearchWord {
  private TrieNode root;

  private static class TrieNode {
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
  }

  /** Initialize your data structure here. */
  public P211AddSearchWord() {
    root = new TrieNode();
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      if (curr.children[c - 'a'] == null) {
        curr.children[c - 'a'] = new TrieNode();
      }
      curr = curr.children[c - 'a'];
    }
    curr.isWord = true;
  }

  /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
  public boolean search(String word) {
    return searchHelper(word, root);
  }

  private boolean searchHelper(String word, TrieNode curr) {
    if (word != null && !word.isEmpty()) {
      char c = word.charAt(0);
      if (c == '.') {
        for (TrieNode child : curr.children) {
          if (child != null) {
            if (searchHelper(word.substring(1), child)) {
              return true;
            }
          }
        }
        // if you reached here, it means you reached the leaf and did not find the word.
        return false;
      } else {
        TrieNode child = curr.children[word.charAt(0) - 'a'];
        return child != null && searchHelper(word.substring(1), child);
      }
    }
    return curr.isWord;
  }

  public static void main(String[] args) {
    P211AddSearchWord addSearchWord = new P211AddSearchWord();
    addSearchWord.addWord("ran");
    addSearchWord.addWord("rune");
    addSearchWord.addWord("runner");
    addSearchWord.addWord("runs");
    addSearchWord.addWord("add");
    addSearchWord.addWord("adds");
    addSearchWord.addWord("adder");
    addSearchWord.addWord("addee");
    System.out.println(addSearchWord.search("......."));
  }
}
