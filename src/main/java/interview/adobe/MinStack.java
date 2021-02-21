package interview.adobe;

import java.util.Stack;

public class MinStack {
  private static class Element {
    private int num;
    private int min;

    public Element(int num, int min) {
      this.num = num;
      this.min = min;
    }
  }
  Stack<Element> stack;
  int min = Integer.MAX_VALUE;

  public MinStack() {
    stack = new Stack<>();
  }

  public void push(int x) {
    if (x <= min) {
      min = x;
    }
    stack.push(new Element(x, min));
  }

  public void pop() {
    if (!stack.isEmpty()) {
      stack.pop();
    }
    if (!stack.isEmpty()) {
      min = stack.peek().min;
    } else {
      min = Integer.MAX_VALUE;
    }
  }

  public int top() {
    return stack.peek().num;
  }

  public int getMin() {
    return stack.peek().min;
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    System.out.println(minStack.getMin());
    minStack.pop();
    minStack.pop();
    minStack.pop();
    minStack.pop();
    minStack.push(-4);
    System.out.println(minStack.top());
    System.out.println(minStack.getMin());
  }
}
