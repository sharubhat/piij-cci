/**
 * A class to try out short snippets in java
 */
public class Test {
  public static void main(String[] args) {
    String code = "00899704-e3c6-4218-b687-f3890d097fda";
    int hashcode = code.hashCode() * -1;
    System.out.println(hashcode);
    String bucket = String.format("%03d", Math.abs(hashcode % 4));
    System.out.println(bucket);

    // StringBuilder setLength() example
    StringBuilder sb = new StringBuilder(3);
    sb.append("hi there");
    System.out.println(sb.toString());
    sb.setLength(4);
    System.out.println(sb.toString());
    sb.append("hello");
    System.out.println(sb.toString());

    System.out.println("---------");
    pagination(101);
    pagination(100);
  }

  private static void pagination(int n) {
    int pages = n / 10 + ((n % 10 > 0) ? 1 : 0);
    System.out.println(pages);

    for (int i = 0; i < pages; i++) {
      int start = i * 10;
      int end = start + 9;
      System.out.println(start + " " + end);
    }
  }
}
