package leetcode.mockinterview;

import java.util.*;

public class P282ExpressionAddOperators {
  public List<String> addOperators(String num, int target) {
    if (num == null || num.length() == 0) {
      return Collections.emptyList();
    }
    if (num.length() == 1 && Integer.parseInt(num) == target) {
      return Arrays.asList(new String[] {num});
    }
    List<String> result = new ArrayList<>();
    addOperators(num, 1, target, result);
    return result;
  }

  private void addOperators(String expression, int index, int target, List<String> result) {
    if (expression.length() == index) {
      if (evaluate(expression) == target) {
        result.add(expression);
      }
      return;
    }
    addOperators(
        expression.substring(0, index) + "+" + expression.substring(index),
        index + 2,
        target,
        result);
    addOperators(
        expression.substring(0, index) + "-" + expression.substring(index),
        index + 2,
        target,
        result);
    addOperators(
        expression.substring(0, index) + "*" + expression.substring(index),
        index + 2,
        target,
        result);
  }

  // this is really evaluating generic expression, this is simply handling this special case with no
  // braces
  private int evaluate(String expression) {
    int result = Character.getNumericValue(expression.charAt(0));
    int i = 1;
    while (i < expression.length()) {
      int rightOperand = Character.getNumericValue(expression.charAt(i + 1));
      char operator = expression.charAt(i);
      char nextOperator = ' ';
      if (i + 2 < expression.length()) {
        nextOperator = expression.charAt(i + 2);
      }
      if (nextOperator == '*') {
        int nextProduct = rightOperand * Character.getNumericValue(expression.charAt(i + 3));
        if (operator == '+') {
          result += nextProduct;
        } else if (operator == '-') {
          result -= nextProduct;
        } else {
          result *= nextProduct;
        }
        i = i + 4;
      } else {
        if (operator == '+') {
          result += rightOperand;
        } else if (operator == '-') {
          result -= rightOperand;
        } else {
          result *= rightOperand;
        }
        i = i + 2;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(new P282ExpressionAddOperators().addOperators("232", 8));
  }
}
