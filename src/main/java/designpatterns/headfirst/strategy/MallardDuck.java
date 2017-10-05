package designpatterns.headfirst.strategy;

/**
 * Created by sharath on 1/25/17.
 */
public class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm real Mallard duck");
    }
}
