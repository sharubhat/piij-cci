package java8.concurrency.synchronizationobjects.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CDLDemo {
  public static void main(String[] args) {
    CountDownLatch cdl = new CountDownLatch(5);

    System.out.println("Starting...");

    new Thread(new MyThread(cdl)).start();

    try {
      cdl.await();
    } catch (InterruptedException e) {
      System.out.println("Interrupted");
    }

    System.out.println("Done");
  }
}

class MyThread implements Runnable {
  CountDownLatch cdl;

  public MyThread(CountDownLatch cdl) {
    this.cdl = cdl;
  }

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(i);
      cdl.countDown();
    }
  }
}