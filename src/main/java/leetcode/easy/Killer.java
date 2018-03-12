package leetcode.easy;

import java.util.LinkedList;
import java.util.List;
// killing the person next to you while standing in a circle until only one remains.
public class Killer {
  public static void main(String[] args) {
    List<Integer> list = new LinkedList<>();
    for (int i = 1; i <= 100; i++) {
      list.add(i);
    }

    int i = 0;
    while(list.size() > 1) {
      ++i;
      if(i >= list.size()) {
        i = i % list.size();
      }
      list.remove(i);
    }
    System.out.println(list.get(0));
  }
}
