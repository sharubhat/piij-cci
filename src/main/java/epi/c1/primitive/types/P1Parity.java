package epi.c1.primitive.types;

import java.util.Arrays;

public class P1Parity {

  private static short[] parity = new short[(2 ^ 16) / 2 - 1];

  static {
    for (int i = 0; i < parity.length; i++) {
      parity[i] = parityFaster(i);
    }
  }

  // parity is 1 if number of set bits is off, 0 if even
  // O(n)
  public short parity(long n) {
    short parity = 0;
    while (n != 0) {
      parity ^= (n & 1);
      n = n >>> 1;   // n & (n - 1) gives n with last set bit unset
    }
    return parity;
  }

  // O(k) algorithm which uses a trick
  // alternative to `parity ^= 1` is to count how many times while loop is executed
  // and check if it is odd or even
  public static short parityFaster(long n) {
    short parity = 0;
    while (n != 0) {
      parity ^= 1;
      n &= n - 1;   // n & (n - 1) gives n with last set bit unset
    }
    return parity;
  }

  public static short[] parity(long[] n) {
    System.out.println(Arrays.toString(parity));
    short[] res = new short[n.length];
    int index = 0;
    for (long l : n) {
      res[index++] = parity[index];
    }
    return res;
  }

  public static void main(String[] args) {
    parity(null);
  }
}
