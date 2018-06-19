package datastructures.suffixarrays;

public class LongestRepeatedSubstring {
  public String longestRepeatedSubString(String text) {
    SuffixArray suffixArray = new SuffixArray(text);
    String lrs = "";
    for (int i = 0; i < text.length(); i++) {
      int length = suffixArray.longestCommonPrefix(i);
      if (length > lrs.length()) {
        lrs = suffixArray.select(i).substring(0, length);
      }
    }
    return lrs;
  }

  public static void main(String[] args) {
    String text =
        "it was the best of times it was the worst of times\n"
            + "it was the age of wisdom it was the age of foolishness\n"
            + "it was the epoch of belief it was the epoch of incredulity\n"
            + "it was the season of light it was the season of darkness\n"
            + "it was the spring of hope it was the winter of despair";
    System.out.println(new LongestRepeatedSubstring().longestRepeatedSubString(text));
    System.out.println("Expected : " + "st of times it was the");
  }
}
