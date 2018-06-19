package java8.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeArrayList<E> {
  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  private final Lock readLock = readWriteLock.readLock();

  private final Lock writeLock = readWriteLock.writeLock();

  private final List<E> list = new ArrayList<>();

  public void set(E o) {
    writeLock.lock();
    try {
      list.add(o);
    } finally {
      writeLock.unlock();
    }
  }

  public E get(int i) {
    readLock.lock();
    try {
      return list.get(i);
    } finally {
      readLock.unlock();
    }
  }

  public static void main(String[] args) {
    ThreadSafeArrayList<String> threadSafeArrayList = new ThreadSafeArrayList<>();
    threadSafeArrayList.set("1");
    threadSafeArrayList.set("2");
    threadSafeArrayList.set("3");
  }
}
