package designpatterns.headfirst.decorator.condiments;

import designpatterns.headfirst.decorator.Beverage;
import designpatterns.headfirst.decorator.CondimentDecorator;

public class Mocha extends CondimentDecorator {
  Beverage beverage;

  public Mocha(Beverage beverage) {
    this.beverage = beverage;
  }

  @Override
  public String getDescription() {
    return beverage.getDescription() + ", Mocha";
  }

  @Override
  public double cost() {
    return 0.20 + beverage.cost();
  }
}
