package java8.concurrency.aompp;

import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock extends LockImpl {
  AtomicBoolean state = new AtomicBoolean(false);

  @Override
  public void lock() {
    while (true) {
      // spin
      while (state.get()) {}
      // if lock is still available, take it
      // else go back to spinning
      if (!state.getAndSet(true)) {
        return;
      }
    }
  }

  @Override
  public void unlock() {
    state.set(false);
  }
}

