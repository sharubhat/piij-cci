package com.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * Min-heap implementation.
 * Few things to keep in mind,
 * a. There is no internal ordering. Left child can be greater than right.
 * b. Always replace the smallest child while bubbling down.
 * c. Ignore first element of store to keep index calculations simpler.
 */
public class Heap {
  private List<Integer> store;
  private int size;

  public Heap() {
    this.store = new ArrayList<>();
    this.store.add(0);
    this.size = 0;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public void add(Integer n) {
    this.store.add(++this.size, n);
    bubbleUp();
    System.out.println(this);
  }

  public Integer peek() {
    return this.store.get(1);
  }

  public Integer remove() {
    if (size <= 0) {
      throw new RuntimeException("Empty heap");
    }
    System.out.println(this.store.subList(0, size + 1));
    Integer min = this.store.get(1);
    this.store.set(1, this.store.get(size));
    this.size--;
    System.out.println(this.store.subList(0, size + 1));
    bubbleDown();
    System.out.println(this.store.subList(0, size + 1));
    return min;
  }

  public String toString() {
    return this.store.subList(0, size + 1).toString();
  }

  private void bubbleDown() {
    int parentIndex = 1;
    int leftChildIndex = getLeftChildIndex(parentIndex);
    int rightChildIndex = leftChildIndex + 1;
    while(childExists(parentIndex)) {
      // very important condition. Replace the child that is smaller.
      boolean replaceRightChild = rightChild(parentIndex) < leftChild(parentIndex);
      int parent = this.store.get(parentIndex);
      if (replaceRightChild && parent > rightChild(parentIndex)) {
        swap(parentIndex, rightChildIndex);
        parentIndex = rightChildIndex;
      } else if (parent > leftChild(parentIndex)) {
        swap(parentIndex, leftChildIndex);
        parentIndex = leftChildIndex;
      } else {
        return;
      }
      leftChildIndex = getLeftChildIndex(parentIndex);
      rightChildIndex = leftChildIndex + 1;
    }
  }

  private Integer rightChild(int parentIndex) {
    int rightChildIndex = getLeftChildIndex(parentIndex) + 1;
    return this.store.get(rightChildIndex);
  }

  private Integer leftChild(int parentIndex) {
    return this.store.get(getLeftChildIndex(parentIndex));
  }

  private boolean childExists(int parentIndex) {
    int leftChildIndex = getLeftChildIndex(parentIndex);
    int rightChildIndex = leftChildIndex + 1;
    return leftChildIndex <= size || rightChildIndex <= size;
  }

  private void bubbleUp() {
    int index = this.size;
    int parentIndex = getParentIndex(index);
    while (parentIndex > 0 &&
            this.store.get(parentIndex).compareTo(this.store.get(index)) > 0) {
      swap(parentIndex, index);
      index = parentIndex;
      parentIndex = getParentIndex(index);
    }
  }

  private void swap(int parentIndex, int index) {
    int tmp = this.store.get(parentIndex);
    this.store.set(parentIndex, this.store.get(index));
    this.store.set(index, tmp);
  }

  private int getParentIndex(int index) {
    return index / 2;
  }

  private int getLeftChildIndex(int index) {
    return index * 2;
  }

  public static void main(String[] args) {
    Heap heap = new Heap();
    heap.add(13);
    heap.add(20);
    heap.add(11);
    heap.add(44);
    heap.add(3);
    heap.add(7);
    heap.add(9);
    // expecting [0, 3, 11, 7, 44, 20, 13, 9]
    System.out.println(heap);

    System.out.println(heap.peek() + "\n");

    while (!heap.isEmpty()) {
      System.out.println(heap.remove());
    }
    heap.add(5);
    heap.add(3);
    heap.add(2);
    heap.add(4);
    System.out.println(heap);
  }
}
