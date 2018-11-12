package java8.concurrency.alternativeprinting;

// this is incorrect and doesn't stop. fix it
public class ThreadPrinterUsingWaitNotify implements Runnable {
  private static final int N = 10;
  private static Object object = new Object();

  @Override
  public void run() {
    for (int i = 0; i < N; i++) {
      synchronized (object) {
        System.out.println(i + "--" + Thread.currentThread().getName());
        object.notify();
        try {
          object.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new ThreadPrinterUsingWaitNotify()).start();
    new Thread(new ThreadPrinterUsingWaitNotify()).start();
  }
}
