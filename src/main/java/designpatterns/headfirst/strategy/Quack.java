package designpatterns.headfirst.strategy;

/**
 * Created by sharath on 1/25/17.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
