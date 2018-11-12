package designpatterns.headfirst.decorator.orders;

import designpatterns.headfirst.decorator.Beverage;
import designpatterns.headfirst.decorator.beverages.DarkRoast;
import designpatterns.headfirst.decorator.beverages.Espresso;
import designpatterns.headfirst.decorator.condiments.Mocha;
import designpatterns.headfirst.decorator.condiments.Whip;

public class StarBuzz {
  public static void main(String[] args) {
    Beverage beverage1 = new Espresso();
    System.out.println(beverage1.getDescription() + " $" + beverage1.cost());

    Beverage beverage2 = new DarkRoast();
    beverage2 = new Mocha(beverage2);
    beverage2 = new Mocha(beverage2);
    beverage2 = new Whip(beverage2);
    System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
  }
}
