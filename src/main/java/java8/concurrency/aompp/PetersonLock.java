package java8.concurrency.aompp;

// combines concepts of LockOne and LockTwo and satisfies mutual exclusion,
// is starvation free and also is deadlock-free
public class PetersonLock extends LockImpl {
  private boolean[] flag = new boolean[2];
  private int victim;

  @Override
  public void lock() {
    int i = ThreadId.get();
    int j = 1 - i;
    flag[i] = true; // i am interested
    victim = i;     // but you go first
    while (flag[j] && victim == i) {} // you are interested and I am the victim, so I wait
                                            // I will proceed with my critical section either when you
                                            // loose interest or when you choose to be the victim.
  }

  @Override
  public void unlock() {
    int i = ThreadId.get();
    flag[i] = false;  // i am no longer interested
  }
}
