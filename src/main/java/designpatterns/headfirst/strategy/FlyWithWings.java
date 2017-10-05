package designpatterns.headfirst.strategy;

/**
 * Created by sharath on 1/25/17.
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}
