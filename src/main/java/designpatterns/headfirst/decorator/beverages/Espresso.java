package designpatterns.headfirst.decorator.beverages;

import designpatterns.headfirst.decorator.Beverage;

public class Espresso extends Beverage {
  public Espresso() {
    description = "Espresso";
  }

  @Override
  public double cost() {
    return 1.99;
  }
}
