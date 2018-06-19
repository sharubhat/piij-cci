package java8.concurrency.aompp;

// incomplete code, just trying out examples from the book
public class CallQueue {
  final static int QUEUE_SIZE = 100;
  int head = 0;
  int tail = 0;
  Call[] calls = new Call[QUEUE_SIZE];

  public synchronized void enq(Call c) {
    calls[(tail++) % QUEUE_SIZE] = c;
  }

  public synchronized Call deq() throws InterruptedException {
    while (head == tail) {
      // release the lock and let other threads proceed
      wait();
    }
    return calls[(head++) % QUEUE_SIZE];
  }

  private class Call {
  }
}
