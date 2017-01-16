package java8.lambdasandstreams.lambda2;

/**
 * Created by sharath on 1/14/17.
 */
@FunctionalInterface
public interface TwoEntryPredicate<T> {
    boolean apply(T t1, T t2);
}
