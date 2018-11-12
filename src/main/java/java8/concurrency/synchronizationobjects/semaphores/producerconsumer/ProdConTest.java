package java8.concurrency.synchronizationobjects.semaphores.producerconsumer;

public class ProdConTest {
  public static void main(String[] args) {
    Q q = new Q();
    new Thread(new Consumer(q)).start();
    new Thread(new Producer(q)).start();
  }
}
