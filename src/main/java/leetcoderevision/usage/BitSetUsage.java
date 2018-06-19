package leetcoderevision.usage;

import java.util.BitSet;

public class BitSetUsage {
  public static void main(String[] args) {
    BitSet set = new BitSet(32);
    System.out.println(set.get(2));
    set.set(2);
    System.out.println(set.get(2));
  }
}
