package com.topcoder;

import java.util.*;

public class MedalTable {
  class Country implements Comparable<Country> {
    public String name;
    public Integer gold;
    public Integer silver;
    public Integer bronze;

    public Country(String name, Integer gold, Integer silver, Integer bronze) {
      this.name = name;
      this.gold = gold;
      this.silver = silver;
      this.bronze = bronze;
    }

    @Override
    public String toString() {
      return name + " " + gold + " " + silver + " " + bronze;
    }

    @Override
    public int compareTo(Country c) {
      if (c.gold != this.gold) {
        return c.gold.compareTo(gold);
      }
      if (c.silver != this.silver) {
        return c.silver.compareTo(silver);
      }
      if (c.bronze != this.bronze) {
        return c.bronze.compareTo(bronze);
      }
      return name.compareTo(c.name);
    }
  }

  public 	String[] generate(String[] results) {
    Map<String, Country> map = new HashMap<>();
    for (String cList : results) {
      String[] countries = cList.split(" ");
      if (map.containsKey(countries[0])) {
        map.get(countries[0]).gold++;
      } else {
        map.put(countries[0], new Country(countries[0], 1, 0, 0));
      }
      if (map.containsKey(countries[1])) {
        map.get(countries[1]).silver++;
      } else {
        map.put(countries[1], new Country(countries[1], 0, 1, 0));
      }
      if (map.containsKey(countries[2])) {
        map.get(countries[2]).bronze++;
      } else {
        map.put(countries[2], new Country(countries[2], 0, 0, 1));
      }
    }
    ArrayList<Country> list = new ArrayList<>();
    list.addAll(map.values());
    Collections.sort(list);
    String[] res = new String[list.size()];
    int i = 0;
    for (Country e : list) {
      res[i++] = e.toString();
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new MedalTable().generate(new String[] {"GER AUT SUI", "AUT SUI GER", "SUI GER AUT"})));
    System.out.println(Arrays.toString(new MedalTable().generate(new String[] {"ITA JPN AUS", "KOR TPE UKR", "KOR KOR GBR", "KOR CHN TPE"})));
  }
}
