package dp.source;

// ref : https://www.youtube.com/watch?v=xdA41xLxwuM
// Length of longest common sub word
public class LongestCommonSubword {
    public static int lcw(String s1, String s2) {
          if (s1.length() == 0 || s2.length() == 0)
            return 0;
          if (s1.charAt(0) == s2.charAt(0))
            return 1 + lcw(s1.substring(1), s2.substring(1));
        else
            return lcw(s1.substring(1), s2.substring(1));
    }

    public static void main(String[] args) {
        System.out.println(lcw("bisect", "trisect"));
        System.out.println(lcw("director", "secretary"));
    }
}
