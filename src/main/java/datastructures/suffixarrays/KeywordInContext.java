package datastructures.suffixarrays;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KeywordInContext {
  // assume Unicode UTF-8 encoding
  private static final String CHARSET_NAME = "UTF-8";

  // assume language = English, country = US for consistency with System.out.
  private static final Locale LOCALE = Locale.US;

  private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

  public List<String> kwic(String text, String key, int context) {
    SuffixArray suffixArray = new SuffixArray(text);
    List<String> result = new ArrayList<>();
    for (int i = suffixArray.rank(key);
         i < text.length() && suffixArray.select(i).startsWith(key);
         i++) {
      int from = Math.max(0, suffixArray.index(i) - context);
      int to = Math.min(text.length() - 1, from + key.length() + 2 * context);
      result.add(text.substring(from, to));
    }
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("./src/main/java/datastructures/suffixarrays/tale.txt");
    if (file.exists()) {
      // for consistency with StdIn, wrap with BufferedInputStream instead of use
      // file as argument to Scanner
      FileInputStream fis = new FileInputStream(file);
      Scanner scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
      scanner.useLocale(LOCALE);
      String text = scanner.useDelimiter(EVERYTHING_PATTERN).next().replaceAll("\\s+", " ");

      System.out.println(new KeywordInContext().kwic(text, "better thing", 15));
    }
  }
}
