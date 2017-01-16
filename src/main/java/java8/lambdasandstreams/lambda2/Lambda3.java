package java8.lambdasandstreams.lambda2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sharath on 1/14/17.
 */
public class Lambda3 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(new String[]{"David", "Mary", "Helen", "Henry", "Bob"});
        List<String> shortWords = StringUtils.allMatches(words, s -> s.length() < 4);
        List<String> wordsWithB = StringUtils.allMatches(words, s -> s.contains("b"));
        List<String> evenLengthWords = StringUtils.allMatches(words, s -> (s.length() % 2) == 0);

        System.out.println(shortWords);
        System.out.println(wordsWithB);
        System.out.println(evenLengthWords);

        List<Integer> nums = Arrays.asList(1, 10, 100, 1000, 10000);
        List<Integer> bigNums = EntryUtil.allMatches(nums, n -> n > 500);
        System.out.println(bigNums);

        List<String> excitingWords = StringUtils.transformedList(words, s -> s + "!");
        List<String> eyeWords = StringUtils.transformedList(words, s -> s.replace("i", "eye"));
        List<String> upperCaseWords = StringUtils.transformedList(words, String::toUpperCase);

        System.out.println(excitingWords);
        System.out.println(eyeWords);
        System.out.println(upperCaseWords);

        List<Integer> wordLengths = EntryUtil.transformedList(words, String::length);
        System.out.println(wordLengths);
    }

}
