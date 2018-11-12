package java8.concurrency.synchronizationobjects.semaphores.producerconsumer;

import java.util.Optional;
import java.util.concurrent.Semaphore;

public class Q {
  Integer n;
  Semaphore consumerSem = new Semaphore(0);
  Semaphore producerSem = new Semaphore(1);

  public Optional<Integer> get() {
    try {
      consumerSem.acquire();
      System.out.println("Consuming " + n);
      return Optional.of(n);
    } catch (InterruptedException e) {
      System.out.println("Interrupted during put.");
    } finally {
      producerSem.release();
    }
    return Optional.empty();
  }

  public void put(Integer n) {
    try {
      producerSem.acquire();
      this.n = n;
      System.out.println("Produced " + n);
    } catch (InterruptedException e) {
      System.out.println("Interrupted during get.");
    } finally {
      consumerSem.release();
    }
  }
}
