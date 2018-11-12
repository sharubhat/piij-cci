package epi.c1.primitive.types;

public class Bootcamp {
  // use short wherever it makes sense to save on memory.
  // short - 16 bit signed integer - min value -32,768 and a max value of 32,767
  // input can be a long too
  private short countBits(int n) {
    short numBits = 0;
    while (n != 0) {  // n can be positive or negative. so n <=0 is incorrect
      numBits += n & 1;
      n = n >>> 1;  // use unsigned right shift to count the sign bit
    }
    return numBits;
  }
}
