package dp;

public class LongestCommonSubsequence {
  private int lcsDynamic(char[] str1, char[] str2) {
    // adding an extra row and column for 0th index to the table.
    // that implies, we need to subtract 1 from both row index and column index of character arrays.
    int[][] table = new int[str1.length + 1][str2.length + 1];
    for (int r = 1; r <= str1.length; r++) {
      for (int c = 1; c <= str2.length; c++) {
        if (str1[r - 1] == str2[c - 1]) {
          table[r][c] = table[r - 1][c - 1] + 1;
        } else {
          table[r][c] = Math.max(table[r - 1][c], table[r][c - 1]);
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int r = table.length - 1, c = table[0].length - 1; r > 0 && c > 0; r--) {
      while (table[r][c] == table[r][c - 1]) {
        c--;
      }
      if (table[r][c] != table[r - 1][c] && table[r][c] == table[r - 1][c - 1] + 1) {
        sb.append(str1[r - 1]);
        c--;
      }
    }
    System.out.println(sb.reverse());
    return table[str1.length][str2.length];
  }

  public static void main(String args[]){
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();
    String str1 = "ABCDGHLQR";
    String str2 = "AEDPHR";

    int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
    System.out.print(result);
  }
}
