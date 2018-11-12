package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameMatching {
  public static boolean name_match(String[] known_aliases, String name) {
    if (checkExactMatch(known_aliases, name))
      return true;
    if (checkNotExactMatch(known_aliases, name))
      return true;
    if (checkTranspose(known_aliases, name)) {
      return true;
    }
    return false;
  }

  private static boolean checkTranspose(String[] known_aliases, String name) {
    for (String alias : known_aliases) {
      Pattern pattern = Pattern.compile("(\\w+)\\s+(\\w+)\\s+(\\w+)");
      Matcher matcherP = pattern.matcher(alias);
      Matcher matcherN = pattern.matcher(name);
      while (matcherP.find() && matcherN.find()) {
        if (matcherP.group(2).matches(matcherN.group(1)) &&
            matcherP.group(3).matches(matcherN.group(3))) {
          if (matcherP.group(1).matches(matcherN.group(2)) ||
              matcherP.group(1).charAt(0) == matcherN.group(2).charAt(0)) {
          return true;
          }
        }
      }
    }
    return false;
  }

  private static boolean checkNotExactMatch(String[] known_aliases, String name) {
    for (String alias : known_aliases) {
      String[] aliasParts = alias.split(" ");
      String[] nameParts = name.split(" ");
      if (aliasParts.length == 2 && nameParts.length == 3) { // alias missing middle name
        if (aliasParts[0].equals(nameParts[0]) && aliasParts[1].equals(nameParts[2])) {
          return true;
        }
      } else if (aliasParts.length == 3 && nameParts.length == 2) { // name missing middle name
        if (aliasParts[0].equals(nameParts[0]) && aliasParts[2].equals(nameParts[1])) {
          return true;
        }
      } else { // middle initial match
        if ((aliasParts[1].length() == 1 || nameParts[1].length() == 1)
            && aliasParts[0].equals(nameParts[0])
            && aliasParts[1].charAt(0) == nameParts[1].charAt(0)
            && aliasParts[2].equals(nameParts[2])) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean checkExactMatch(String[] known_aliases, String name) {
    for (String alias : known_aliases) {
      if (name.matches(alias)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    String[] known_aliases = new String[] {"Alphonse Gabriel Capone", "Al Capone"};
//    System.out.println(name_match(known_aliases, "Alphonse Gabriel Capone"));
//    System.out.println(name_match(known_aliases, "Al Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Francis Capone"));
//
//    known_aliases = new String[] {"Alphonse Capone"};
//    System.out.println(name_match(known_aliases, "Alphonse Gabriel Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Francis Capone"));
//    System.out.println(name_match(known_aliases, "Alexander Capone"));
//
//    known_aliases = new String[] {"Alphonse Gabriel Capone"};
//    System.out.println(name_match(known_aliases, "Alphonse Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Francis Capone"));
//    System.out.println(name_match(known_aliases, "Alexander Capone"));
//
//    known_aliases = new String[] {"Alphonse Gabriel Capone", "Alphonse Francis Capone"};
//    System.out.println(name_match(known_aliases, "Alphonse Gabriel Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Francis Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Edward Capone"));
//
//    known_aliases = new String[] {"Alphonse Gabriel Capone", "Alphonse F Capone"};
//    System.out.println(name_match(known_aliases, "Alphonse G Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Francis Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse E Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Edward Capone"));
//    System.out.println(name_match(known_aliases, "Alphonse Gregory Capone"));

    known_aliases = new String[] {"Alphonse Gabriel Capone"};
    System.out.println(name_match(known_aliases, "Gabriel Alphonse Capone"));
    System.out.println(name_match(known_aliases, "Gabriel A Capone"));
    System.out.println(name_match(known_aliases, "Gabriel Capone"));
    System.out.println(name_match(known_aliases, "Gabriel Francis Capone"));

    String[] names = "Gabriel Francis Capone".split(" ");
    System.out.println(String.join(" ", names));
  }
}
