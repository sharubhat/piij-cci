package designpatterns.headfirst.decorator.beverages;

import designpatterns.headfirst.decorator.Beverage;

public class DarkRoast extends Beverage {
  public DarkRoast() {
    description = "Dark Roast Coffee";
  }

  @Override
  public double cost() {
    return 0.99;
  }
}
