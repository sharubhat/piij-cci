package regex;

import java.util.ArrayList;
import java.util.List;

public class LookAheadExample {
  public static void main(String[] args) {
    List<String> input = new ArrayList<String>();
    input.add("password");
    input.add("p4ssword");
    input.add("p4ssw0rd");
    input.add("p45sword");
    input.add("p45swor4");

    for (String pass : input) {
      if (pass.matches("^(?=.*[0-9].*[0-9])[0-9a-zA-Z]{8,12}$")) {
        System.out.println(pass + " matches");
      } else {
        System.out.println(pass + " does not match");
      }
    }
  }
}
