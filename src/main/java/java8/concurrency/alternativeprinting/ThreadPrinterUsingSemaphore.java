package java8.concurrency.alternativeprinting;

import java.util.concurrent.Semaphore;

public class ThreadPrinterUsingSemaphore implements Runnable {
  int counter;
  Semaphore ins, outs;

  public ThreadPrinterUsingSemaphore(int counter, Semaphore ins, Semaphore outs) {
    this.counter = counter;
    this.ins = ins;
    this.outs = outs;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < 25; i++) {
        ins.acquire();
        System.out.println("" + counter);
        outs.release();
        counter += 2;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Semaphore ins = new Semaphore(1);  // first thread is allowed to run immediately
    Semaphore outs = new Semaphore(0); // second thread has to wait
    ThreadPrinterUsingSemaphore tp1 = new ThreadPrinterUsingSemaphore(1, ins, outs);
    ThreadPrinterUsingSemaphore tp2 = new ThreadPrinterUsingSemaphore(2, outs, ins);
    new Thread(tp1).start();
    new Thread(tp2).start();
  }
}
