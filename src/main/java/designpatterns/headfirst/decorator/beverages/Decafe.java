package designpatterns.headfirst.decorator.beverages;

import designpatterns.headfirst.decorator.Beverage;

public class Decafe extends Beverage {
  public Decafe() {
    description = "Decafe";
  }

  @Override
  public double cost() {
    return 1.05;
  }
}
