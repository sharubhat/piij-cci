package java8.concurrency.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class PhaserWorker implements Runnable {
  private List<String> outputScraper;
  private Phaser callingThreadBlocker;
  private CountDownLatch completedThreadCounter;
  private AtomicBoolean processing;

  public PhaserWorker(List<String> outputScraper,
                      Phaser callingThreadBlocker,
                      CountDownLatch completedThreadCounter,
                      AtomicBoolean processing) {
    this.outputScraper = outputScraper;
    this.callingThreadBlocker = callingThreadBlocker;
    this.processing = processing;
    this.completedThreadCounter = completedThreadCounter;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() / 1000);
    if (!processing.getAndSet(true)) {
      callingThreadBlocker.register();
      try {
        System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() / 1000 + " Starting to work");
        doSomeWork();
        System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() / 1000 + " Work completed");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        callingThreadBlocker.arriveAndAwaitAdvance();
        processing.set(false);
      }
    } else {
        callingThreadBlocker.register();
        System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() / 1000  + " Someone else is working");
        callingThreadBlocker.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() / 1000  + " Someone else finished working");
    }
    System.out.println("Completed phases " + System.currentTimeMillis() / 1000  + " " + callingThreadBlocker.getPhase());
    callingThreadBlocker.arriveAndDeregister();
    completedThreadCounter.countDown();
  }

  private void doSomeWork() throws InterruptedException {
    TimeUnit.SECONDS.sleep(5);
  }
}
