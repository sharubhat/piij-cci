package javachallenge;

/**
 * Learning notes:
 * Fields can not be overridden. Non-static methods are overridden.
 */
public class PolymorphismChallenge5 {
  public static void main(String[] args) {
    Dracula dracula = new Alucard();
    System.out.println(dracula.name + " " + dracula.getName());
  }

  static class Dracula {
    String name = "Dracula";

    String getName() {
      return name;
    }
  }

  static class Alucard extends Dracula {
    String name = "Alucard";

    String getName() {
      return name;
    }
  }
}
