package java8.basics;

import java.util.*;

public class MapCompute {
  public static void main(String[] args) {
    Map<String, List<String>> map = new HashMap<>();
    map.put("k1", new ArrayList<>());
    map.put("k2", null);
    map.get("k1").add("v1");
    map.get("k1").add("v2");

    System.out.println(map);

    map.compute("k2", (k, v) -> {
      if (v == null) {
        return new ArrayList();
      }
      else {
        v.add("v3");
        return v;
      }
    });

    System.out.println(map);

    List<Integer> list = new ArrayList<>();
    list.add(5);
    list.add(4);
    list.add(3);
    Collections.sort(list, (a, b) -> a.compareTo(b));
    System.out.println(list);
  }
}
