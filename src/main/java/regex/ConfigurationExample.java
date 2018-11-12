package regex;

public class ConfigurationExample {
  public static void main(String[] args) {
    String input = "My dog is Blue.\n" +
        "He is not red or green.";

    boolean controlResult = input.matches("(?=.*Green.*).*Blue.*");


    System.out.println("Control result was: " + controlResult);
  }
}
