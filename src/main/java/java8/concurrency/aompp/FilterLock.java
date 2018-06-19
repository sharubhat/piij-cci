package java8.concurrency.aompp;

public class FilterLock extends LockImpl {
  static final int IDLE = -1;
  int[] level;
  int[] victim;
  int size;

  public FilterLock(int threads) {
    level = new int[threads];
    victim = new int[threads - 1];
    this.size = size;
  }

  @Override
  public void lock() {
    int me = ThreadId.get();
    // for one level at a time
    for (int L = 0; L < size - 1; L++) {
      // i intend to enter level L
      level[me] = L;
      // i will be victim, give priority to anyone but me
      victim[L] = me;

      // spin while conflicts exist
      // while (∃k != me) (level[k] >= i && victim[i] == me)
      // or while "there exists some k other than me such that level[k] >= i && victim[i] == me"
      // otherwise - I am willing to wait as long as someone else is at same or higher level,
      // and I am designated victim at current level
      while (sameOrHigher(me, L) && victim[L] == me) {}
      // thread enters level L when it completes the loop
    }
    level[me] = size - 1;
  }

  private boolean sameOrHigher(int me, int myLevel) {
    for (int id = 0; id < size; id++) {
      if (id != me && level[id] >= myLevel) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void unlock() {
    int me = ThreadId.get();
    level[me] = IDLE;
  }
}
