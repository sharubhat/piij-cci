package java8.concurrency.aompp;

// two thread lock algorithm
// deadlock occurs if thread executions are interleaved, i. e.
// if both threads set flag to true before one has opportunity to check the flag set by other.
public class LockOne extends LockImpl {
  private boolean[] flag = new boolean[2];

  // thread-local index, 0 or 1
  @Override
  public void lock() {
    int i = ThreadId.get();
    int j = 1 - i;
    flag[i] = true;
    while (flag[j]) {}  // wait
  }

  @Override
  public void unlock() {
    int i = ThreadId.get();
    flag[i] = false;
  }
}
