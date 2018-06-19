package java8.jbc.fizzbuzz;

public class ConsoleBasedFizzBuzz implements FizzBuzz {
  @Override
  public void print(int from, int to) {
    for (int i = from; i <= to; i++) {
      if (i % 3 == 0) {
        if (i % 5 == 0) {
          System.out.println("FizzBuzz");
        } else {
          System.out.println("Fizz");
        }
      } else if (i % 5 == 0) {
        System.out.println("Buzz");
      } else {
        System.out.println(i);
      }
    }
  }
}
