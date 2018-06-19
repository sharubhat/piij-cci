package java8.concurrency.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class OnlyFirstComeProcessWorker implements Runnable {
  private List<String> outputScraper;
  private CountDownLatch callingThreadBlocker;
  private CountDownLatch completedThreadCounter;
  private AtomicBoolean processing;

  public OnlyFirstComeProcessWorker(List<String> outputScraper,
                                    CountDownLatch callingThreadBlocker,
                                    CountDownLatch completedThreadCounter,
                                    AtomicBoolean processing) {
    this.outputScraper = outputScraper;
    this.callingThreadBlocker = callingThreadBlocker;
    this.processing = processing;
    this.completedThreadCounter = completedThreadCounter;
  }

  @Override
  public void run() {
    outputScraper.add(Thread.currentThread().getName() + " " + callingThreadBlocker.getCount());
    if (!processing.getAndSet(true)) {
      try {
        outputScraper.add(Thread.currentThread().getName() + " Starting to work");
        doSomeWork();
        outputScraper.add(Thread.currentThread().getName() + " Work completed");
        outputScraper.add(Thread.currentThread().getName() + " Counted down");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        callingThreadBlocker.countDown();
        processing.set(false);
      }
    } else {
      try {
        outputScraper.add(Thread.currentThread().getName() + " Someone else is working");
        callingThreadBlocker.await();
        outputScraper.add(Thread.currentThread().getName() + " Someone else finished working");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    completedThreadCounter.countDown();
  }

  private void doSomeWork() throws InterruptedException {
    TimeUnit.SECONDS.sleep(3);
  }
}
