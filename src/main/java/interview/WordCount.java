package interview;

public class WordCount {
  public static boolean isSpace(char c) {
    return c == ' ' || c == '\n' || c == '\t';
  }

  public static int wordCount(String s) {
    boolean isPrevSpaceChar = true;
    int wc = 0;
    for (int i = 0; i < s.length(); i++) {
      if (isSpace(s.charAt(i))) {
        isPrevSpaceChar = true;
      } else {
        if (isPrevSpaceChar) {
          wc++;
          isPrevSpaceChar = false;
        }
      }
    }
    return wc;
  }

  public static void main(String[] args) {
    System.out.println(wordCount(""));
    System.out.println(wordCount(" "));
    System.out.println(wordCount(" hello world "));
    System.out.println(wordCount("   hello\n"));
    System.out.println(wordCount("   hello\n  world \t   !"));
  }
}
