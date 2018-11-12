package java8.concurrency.synchronizationobjects.semaphores.producerconsumer;

public class Producer implements Runnable {
  Q q;

  public Producer(Q q) {
    this.q = q;
  }

  @Override
  public void run() {
    for (int i = 0; i < 20; i++) {
      q.put(i);
    }
  }
}
