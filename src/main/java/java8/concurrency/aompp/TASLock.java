package java8.concurrency.aompp;

import java.util.concurrent.atomic.AtomicBoolean;

public class TASLock extends LockImpl {
  AtomicBoolean state = new AtomicBoolean(false);

  @Override
  public void lock() {
    while (state.getAndSet(true)){}
  }

  @Override
  public void unlock() {
    state.set(false);
  }
}
