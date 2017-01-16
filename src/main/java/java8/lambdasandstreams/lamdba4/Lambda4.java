package java8.lambdasandstreams.lamdba4;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sharath on 1/14/17.
 */
public class Lambda4 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("hi", "hello", "bye", "goodbye");
        System.out.println(FunctionUtils.firstAllMatch(words.stream(),
                word -> word.contains("o"),
                word -> word.length() > 5));

        System.out.println(FunctionUtils.firstAnyMatch(words.stream(),
                word -> word.contains("o"),
                word -> word.length() > 5));
    }
}
