package codechef;

import java.util.*;

/**
 * https://www.codechef.com/problems/VOTERS
 */
public class P2Voters {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Set<Integer> first = new HashSet<>();
    Set<Integer> second = new HashSet<>();
    int[] lengths = new int[3];

    String[] firstList = scanner.nextLine().split(" ");
    int index = 0;
    for (String s : firstList) {
      lengths[index++] = Integer.parseInt(s);
    }

    for (int i = 0; i < lengths[0]; i++) {
      first.add(Integer.parseInt(scanner.nextLine()));
    }

    for (int i = 0; i < lengths[1] + lengths[2]; i++) {
      int val = Integer.parseInt(scanner.nextLine());
      if (!first.add(val)) {
        second.add(val);
      }
    }

    List<Integer> res = new ArrayList<>(second);
    Collections.sort(res);

    System.out.println(res.size());
    for (int i : res) {
      System.out.println(i);
    }
  }
}
