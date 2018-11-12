package regex;

import java.util.Arrays;

public class Splits {
  public static void main(String[] args) {
    String input = "We came, we saw, we conquered...then we ate Bill's (Mille-Feuille) cake.";
    // Get list of words. In other words, split when there is one or more non-word character
    System.out.println(Arrays.toString(input.split("\\W+")));
    // or
    System.out.println(Arrays.toString(input.split("[^\\w]+")));
    // if you want to see Bill's as one word or Mille-Feuille as one word
    System.out.println(Arrays.toString(input.split("[^-a-zA-Z']+")));
  }
}
