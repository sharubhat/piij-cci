package java8.concurrency.aompp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Second attempt. Each thread now takes a number at a time and
 * checks if it is a prime. This takes care of some numbers taking longer
 * than others for verification. But the results will be inaccurate.
 *
 * -- Synchronizing getAndIncrement didn't help. Need to understand why.
 * Update -- Figured out the problem. Creating counter per runnable will result in
 * incorrect computation. Both counter and count need to be shared and hence
 * should be instantiated within main. Also, checking i and then incrementing
 * will result in incorrect value because more than one thread might have
 * checked i before calling counter.getAndIncrement().
 *
 * while ((i = counter.getAndIncrement()) < limit) is correct
 *
 * while (i < limit) { // is wrong
 *   i = counter.getAndIncrement();
 * }
 * Further update -- learning to use locks. The code is updated.
 * Always unlock inside finally block.
 */
public class PrintPrime2 implements Runnable {
  private Counter counter;
  private AtomicInteger count;

  public PrintPrime2(Counter counter, AtomicInteger count) {
    this.counter = counter;
    this.count = count;
  }

  @Override
  public void run() {
    double i;
    double limit = 10e5;
    while ((i = counter.getAndIncrement()) < limit) {
      if (PrintPrime.isPrime(i)) {
        System.out.println(i);
        count.incrementAndGet();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    AtomicInteger count = new AtomicInteger();
    Counter counter = new Counter(1);

    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new PrintPrime2(counter, count));
      threads[i].start();
    }
    for (int i = 0; i < threads.length; i++) {
      threads[i].join();
    }
    System.out.println(count.get() + " " + (count.get() == 78498));
  }
}

class Counter {
  private long i;
  private Lock mutex;

  public Counter(long count) {
    this.i = count;
    this.mutex = new ReentrantLock();
  }

  public long getAndIncrement() {
    mutex.lock();
    try {
      long tmp = i;
      i++;
      return tmp;
    } finally{
      mutex.unlock();
    }
  }
}