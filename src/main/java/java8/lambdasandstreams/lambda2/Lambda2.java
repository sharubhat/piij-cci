package java8.lambdasandstreams.lambda2;

/**
 * Created by sharath on 1/14/17.
 */
public class Lambda2 {
    /*
        Making your own interfaces for which lambdas can be used. Your eventual goal is to make a method called betterString that takes two Strings and a lambda that says whether the first of the two is “better”. The method should return that better String; i.e., if the function given by the lambda returns true, the betterString method should return the first String, otherwise betterString should return the second String. Here are two examples of how your code should work when it is finished (the first example returns whichever of string1 and string2 is longer, and the second example always returns string1).
        • StringUtils.betterString(string1, string2, (s1, s2) -> s1.length() > s2.length())
        • StringUtils.betterString(string1, string2, (s1, s2) -> true) Accomplishing all of this requires you to do three things:
        • Define the TwoStringPredicate interface. It will specify a method that takes 2 strings and returns a boolean. This interface is normal Java 7 code (except that in later sections we will introduce the optional but highly recommended @FunctionalInterface annotation).
        • Define the static method betterString. That method will take 2 strings and an instance of your interface. It returns string1 if the method in interface returns true, string2 otherwise. This method is normal Java 7 code in every way.
        • Call betterString. You can now use lambdas for the 3rd argument, as in the examples above.
     */
    public static void main(String[] args) {
        String s1 = "Revert";
        String s2 = "Commit";
        System.out.println(StringUtils.betterString(s1, s2, (st1, st2) -> st1.length() > st2.length()));
        System.out.println(StringUtils.betterString(s1, s2, (st1, st2) -> true));
    }
}
