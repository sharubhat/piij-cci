package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarness {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (true) {

      System.out.print("Enter your regex: ");
      String regex = in.nextLine();
      Pattern pattern = Pattern.compile(regex);

      System.out.print("Enter input string to search: ");
      String match = in.nextLine();

      Matcher matcher = pattern.matcher(match);

      boolean found = false;
      while (matcher.find()) {
        System.out.format(
            "I found the text" + " \"%s\" starting at " + "index %d and ending at index %d.%n",
            matcher.group(), matcher.start(), matcher.end());
        found = true;
      }
      if (!found) {
        System.out.println("No match found.");
      }
    }
  }
}
