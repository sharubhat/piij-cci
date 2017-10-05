package designpatterns.headfirst.strategy;

/**
 * Created by sharath on 1/25/17.
 */
public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm model duck!");
    }
}
