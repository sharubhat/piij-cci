package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractExamples {
  public static void main(String[] args) {
    String input = "I have a cat, but I like my dog better";

    Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
    Matcher m = p.matcher(input);

    List<String> animals = new ArrayList<>();
    while (m.find()) {
      System.out.println("Found a " + m.group() + ".");
      animals.add(m.group());
    }

    String input2 = "It's sad I lost my wallet and \n also I lost my pen. \n but not my phone";
    Pattern p2 = Pattern.compile("(.*I lost my.*)");
    Matcher m2 = p2.matcher(input2);

    while (m2.find()) {
      System.out.println(m2.group());
    }
  }
}
