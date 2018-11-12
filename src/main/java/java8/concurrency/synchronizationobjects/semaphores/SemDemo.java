package java8.concurrency.synchronizationobjects.semaphores;

import java.util.concurrent.Semaphore;

public class SemDemo {
  public static void main(String[] args) {
    Semaphore sem = new Semaphore(1);

    new Thread(new IncThread(sem, "A")).start();
    new Thread(new DecThread(sem, "B")).start();
  }
}

class Shared {
  static int count = 0;
}

class IncThread implements Runnable {
  String name;
  Semaphore sem;

  public IncThread(Semaphore sem, String name) {
    this.name = name;
    this.sem = sem;
  }

  @Override
  public void run() {
    System.out.println("Starting " + name);

    try {
      // First get a permit.
      System.out.println(name + " is requesting for a permit");
      sem.acquire();
      System.out.println(name + " gets a permit");

      // Now, access shared resource.
      for (int i = 0; i < 5; i++) {
        Shared.count++;
//        System.out.println(name + ": " + Shared.count);
        // Now, allow a context switch -- if possible.
        Thread.sleep(10);
      }
      System.out.println(name + ": " + Shared.count);
    } catch (InterruptedException e) {
      System.out.println(e);
    } finally {
      System.out.println(name + " releases the permit.");
      sem.release();
    }
  }
}

class DecThread implements Runnable {
  String name;
  Semaphore sem;

  public DecThread(Semaphore sem, String name) {
    this.name = name;
    this.sem = sem;
  }

  @Override
  public void run() {
    System.out.println("Starting " + name);

    try {
      // First get a permit.
      System.out.println(name + " is requesting for a permit");
      sem.acquire();
      System.out.println(name + " gets a permit");

      // Now, access shared resource.
      for (int i = 0; i < 5; i++) {
        Shared.count--;
//        System.out.println(name + ": " + Shared.count);
        // Now, allow a context switch -- if possible.
        Thread.sleep(10);
      }
      System.out.println(name + ": " + Shared.count);
    } catch (InterruptedException e) {
      System.out.println(e);
    } finally {
      System.out.println(name + " releases the permit.");
      sem.release();
    }
  }
}