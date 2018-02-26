package leetcode.hard;

public class P273IntegerToEnglishWords {
  private static final String[] BELOW_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
  private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

  public String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }
    int i = 0;
    String words = "";

    while ( num > 0) {
      if (num % 1000 != 0) {
        words = helper(num % 1000) + THOUSANDS[i] + " " + words;
      }
      num /= 1000;
      i++;
    }
    return words.trim();
  }

  private String helper(int num) {
    System.out.println(num);
    if (num == 0) {
      return "";
    } else if (num < 20) {
      return BELOW_20[num] + " ";
    } else if(num < 100) {
      return TENS[num / 10] + " " + helper(num % 10);
    } else {
      return BELOW_20[num / 100] + " " + "Hundred " + helper(num % 100);
    }
  }

  public static void main(String[] args) {
    System.out.println(new P273IntegerToEnglishWords().numberToWords(1_903_000_739));
  }
}
