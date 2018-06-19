package java8.concurrency.aompp;

public class ThreadId {
  private static volatile int nextId = 0;

  private static class ThreadLocalId extends ThreadLocal<Integer> {
    protected synchronized Integer initialValue() {
      return nextId++;
    }
  }

  private static ThreadLocalId threadId = new ThreadLocalId();

  public static int get() {
    return threadId.get();
  }

  public static void set(int index) {
    threadId.set(index);
  }

  public static void main(String[] args) {
    Thread thread = new Thread(() -> System.out.println("Hello from thread : " + ThreadId.get()));
    thread.start();
  }
}
