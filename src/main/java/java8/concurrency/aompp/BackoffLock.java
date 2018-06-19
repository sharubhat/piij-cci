package java8.concurrency.aompp;

import java.util.concurrent.atomic.AtomicBoolean;

public class BackoffLock extends LockImpl {
  private AtomicBoolean state = new AtomicBoolean(false);
  private static final int MIN_DELAY = 5;
  private static final int MAX_DELAY = 50;

  @Override
  public void lock() {
    Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
    while (true) {
      while (state.get()) {}
      if (!state.getAndSet(true)) {
        return;
      } else {
        try {
          backoff.backoff();
        } catch (InterruptedException e) {
          // do nothing
        }
      }
    }
  }

  @Override
  public void unlock() {
    state.set(false);
  }
}
