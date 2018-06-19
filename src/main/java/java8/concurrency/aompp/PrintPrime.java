package java8.concurrency.aompp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * First attempt to print primes between 1 to 10e5 given we can create 10 threads and
 * we pay by amount of time taken for the program to run.
 * We split 10e5 numbers equally between 10 threads. But this does not guarantee that all threads
 * run for equal amount of time and hence not the most cost optimal implementation.
 */
public class PrintPrime implements Runnable {
  public static AtomicInteger count = new AtomicInteger();

  @Override
  public void run() {
    int i = ThreadId.get();
    double block = 10e4;
    for (double j = (i * block) + 1; j <= (i + 1) * block; j++) {
      if (isPrime(j)) {
        System.out.println(j);
        count.incrementAndGet();
        //        System.out.println("Thread " + i + " : " + j);
      }
    }
  }

  public static boolean isPrime(double num) {
    if (num < 2) return false;
    if (num == 2) return true;
    if (num % 2 == 0) return false;
    for (int i = 3; i * i <= num; i += 2) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) throws InterruptedException {
    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new PrintPrime());
      threads[i].start();
    }
    for (int i = 0; i < threads.length; i++) {
      threads[i].join();
    }
    System.out.println(count.get() + " " + (count.get() == 78498));
  }
}
