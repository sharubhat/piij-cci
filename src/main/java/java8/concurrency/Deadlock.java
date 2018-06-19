package java8.concurrency;

/** https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html */
public class Deadlock {
  static class Friend {
    private final String name;

    public Friend(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }

    public synchronized void bow(Friend bower) {
      System.out.format("%s : %s: %s" + "  has bowed to me!%n", Thread.currentThread().getName(), this.name, bower.getName());
      bower.bowBack(this);
    }

    public synchronized void bowBack(Friend bower) {
      System.out.format("%s: %s" + " has bowed back to me!%n", this.name, bower.getName());
    }
  }

  public static void main(String[] args) throws InterruptedException {
    final Friend alphonse = new Friend("Alphonse");
    final Friend gaston = new Friend("Gaston");
    // Thread-0 acquires lock on alphonse and then tries to acquire lock on gaston
    new Thread(() -> alphonse.bow(gaston)).start();
    // Introducing sleep can allow Thread-0 to release both locks (forced happens-before relationship)
//    Thread.sleep(4000);
    // Thread-1 acquires lock on gaston and then tries to acquire lock on alphonse
    new Thread(() -> gaston.bow(alphonse)).start();
  }
}
