package java8.jbc.fizzbuzz;

public class Main {
  public static void main(String[] args) {
    int max = 100;
    if (args.length > 0) {
      max = Integer.parseInt(args[0]);
    }
    FizzBuzz fizzBuzz = new ConsoleBasedFizzBuzz();
    fizzBuzz.print(1, max);
  }
}
