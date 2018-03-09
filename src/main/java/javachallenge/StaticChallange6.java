package javachallenge;

/**
 * Learning notes:
 * Fields and static methods can't be overridden. Non static method gets overridden.
 */
public class StaticChallange6 {
  public static void main(String[] doYourBest) {
    Homer bart = new Bart();
    System.out.println(bart.age + " " + bart.getAge());
    Homer2 bart2 = new Bart2();
    System.out.println(bart2.age + " " + bart2.getAge());
  }

  static class Homer {
    static int age = 35;
    static int getAge() {
      return age;
    }
  }

  static class Bart extends Homer {
    static int age = 8;
    static int getAge() {
      return age;
    }
  }
}

class Homer2 {
  int age = 35;
  int getAge() {
    return age;
  }
}

class Bart2 extends Homer2 {
  int age = 8;
  int getAge() {
    return age;
  }
}
