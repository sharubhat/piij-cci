package java8.concurrency.guardedblocks;

import java.util.Random;

public class Producer implements Runnable {
  private Drop drop;

  public Producer(Drop drop) {
    this.drop = drop;
  }

  @Override
  public void run() {
    String[] impInfo = {
        "Mares eat oats",
        "Does eat oats",
        "Little lambs eat ivy",
        "A kid will eat ivy too"
    };

    Random random = new Random();

    for (int i = 0; i < impInfo.length; i++) {
      drop.offer(impInfo[i]);
      try {
        Thread.sleep(random.nextInt(5000));
      } catch (InterruptedException e) {}
    }
    drop.offer("DONE");
  }
}
