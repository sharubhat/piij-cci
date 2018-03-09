package leetcode.medium;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
 *
 */
public class P208Trie {
  private static class TrieNode {
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
  }

  private TrieNode root;
  /** Initialize your data structure here. */
  public P208Trie() {
    this.root = new TrieNode();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      if (curr.children[c - 'a'] == null) {
        curr.children[c - 'a'] = new TrieNode();
      }
      curr = curr.children[c - 'a'];
    }
    curr.isWord = true;
  }

  /** Returns if the word is in the trie. */
  public boolean search2(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      if (curr.children[c - 'a'] == null) {
        return false;
      }
      curr = curr.children[c - 'a'];
    }
    return curr.isWord;
  }

  public boolean search(String word) {
    return searchHelper(word, root);
  }

  private boolean searchHelper(String word, TrieNode curr) {
    if (word != null && !word.isEmpty()) {
      TrieNode child = curr.children[word.charAt(0) - 'a'];
      return child != null && searchHelper(word.substring(1), child);
    }
    return curr.isWord;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    TrieNode curr = root;
    for (char c : prefix.toCharArray()) {
      if (curr.children[c - 'a'] == null) {
        return false;
      }
      curr = curr.children[c - 'a'];
    }
    return true;
  }

  public static void main(String[] args) {
    P208Trie trie = new P208Trie();
    trie.insert("hello");
    System.out.println(trie.search("hell"));
    System.out.println(trie.search("helloa"));
    System.out.println(trie.search("hello"));
    System.out.println(trie.startsWith("hell"));
    System.out.println(trie.startsWith("helloa"));
    System.out.println(trie.startsWith("hello"));
  }

  /**
   * Your Trie object will be instantiated and called as such:
   * Trie obj = new Trie();
   * obj.insert(word);
   * boolean param_2 = obj.search(word);
   * boolean param_3 = obj.startsWith(prefix);
   */
}
