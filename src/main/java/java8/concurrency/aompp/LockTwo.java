package java8.concurrency.aompp;

// this does the opposite of LockOne. If both threads run concurrently, they will be executed fine.
// if they run one after another, then end up in a deadlock.
public class LockTwo extends LockImpl {
  private int victim;

  @Override
  public void lock() {
    int i = ThreadId.get();
    victim = i;
    while (victim == i) {}
  }

  @Override
  public void unlock() {

  }
}
