package designpatterns.headfirst.decorator.condiments;

import designpatterns.headfirst.decorator.Beverage;

public class Whip extends Beverage {
  Beverage beverage;

  public Whip(Beverage beverage) {
    this.beverage = beverage;
  }

  @Override
  public String getDescription() {
    return beverage.getDescription() + ", Whip";
  }

  @Override
  public double cost() {
    return 0.10 + beverage.cost();
  }
}
