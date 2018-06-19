package java8.concurrency.aompp;

public class HelloWorld implements Runnable {
  String message;

  public HelloWorld(String message) {
    this.message = message;
  }

  @Override
  public void run() {
    System.out.println(message);
  }

  public static void main(String[] args) throws InterruptedException {
    Thread[] threads = new Thread[8];
    for (int i = 0; i < threads.length; i++) {
      final String m = "Hello from thread - " + i;
      threads[i] = new Thread(() -> System.out.println(m));
    }
    for (int i = 0; i < threads.length; i++) {
      threads[i].start();
    }
    for (int i = 0; i < threads.length; i++) {
      threads[i].join();
    }
    System.out.println("We are done here!");
  }
}
