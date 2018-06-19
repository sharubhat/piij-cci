package java8.concurrency.aompp;

import java.util.Random;

/**
 * adaptive backoff logic. To ensure that concurrently contending threads do not
 * repeatedly try to acquire the lock at the same time,
 * threads back off for a random duration.
 * Each time the thread tries and fails to get the lock,
 * it doubles the expected time to back off, up to a fixed maximum.
 */
public class Backoff {
  final int minDelay;
  int limit;
  final int maxDelay;
  final Random random;

  public Backoff(int min, int max) {
    this.minDelay = min;
    this.maxDelay = max;
    this.random = new Random();
  }

  public void backoff() throws InterruptedException {
    int delay = random.nextInt(limit);
    limit = Math.min(maxDelay, limit * 2);
    Thread.sleep(delay);
  }
}
