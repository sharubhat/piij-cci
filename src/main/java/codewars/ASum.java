package codewars;

public class ASum {

  public static long findNb(long m) {
    long sum = 0;
    long i = 0;
    for (i = 1; sum < m; i++) {
      sum += Math.pow(i, 3);
      System.out.println(sum);
    }
    System.out.println(sum);
    System.out.println(i);
    if (m == sum) {
      return i - 1;
    } else {
      return -1;
    }
  }

  public static void main(String[] args) {
    System.out.println(findNb(2240688563635420901L));
  }
}