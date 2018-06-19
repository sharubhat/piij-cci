package java8.concurrency;

/**
 * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
 */
public class ShadowTest {
  public int x = 0;

  class FirstLevel {
    // shadows the outer x
    public int x = 1;

    void methodInFirstLevel(int x) {
      // method parameter x shadows outer x
      System.out.println("x : " + x); // method parameter
      // this refers to current object which is inner class object
      System.out.println("this.x : " + this.x);
      // refer to outer class instance by ClassName.this
      System.out.println("ShadowTest.this.x : " + ShadowTest.this.x);

    }
  }

  public static void main(String[] args) {
    ShadowTest st = new ShadowTest();
    ShadowTest.FirstLevel fl = st.new FirstLevel();
    fl.methodInFirstLevel(23);
  }
}
