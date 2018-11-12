package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindReplace {
  public static void main(String[] args) {
    String input = "User clientId=23421. Some more text clientId=33432. This clientNum=100";

    Pattern p = Pattern.compile("(clientId=)(\\d+)");
    Matcher m = p.matcher(input);

    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      System.out.println("Masking : " + m.group(2));
      m.appendReplacement(sb, m.group(1) + "***masked***");
    }
    // this is important. otherwise text after last masking will not be appended.
    m.appendTail(sb);
    System.out.println(sb);
  }
}
