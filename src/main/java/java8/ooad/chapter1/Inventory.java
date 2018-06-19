package java8.ooad.chapter1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {
  private List guitars;

  public Inventory() {
    this.guitars = new ArrayList();
  }

  public void addGuitar(
      String serialNumber,
      Double price,
      Builder builder,
      String model,
      Type type,
      Wood backWood,
      Wood topWood) {
    Guitar guitar = new Guitar(serialNumber, builder, model, type, backWood, topWood, price);
    guitars.add(guitar);
  }

  public Guitar getGuitar(String serialNumber) {
    for (Iterator i = guitars.iterator(); i.hasNext();) {
      Guitar guitar = (Guitar)i.next();
      if (guitar.getSerialNumber().equals(serialNumber)) {
        return guitar;
      }
    }
    return null;
  }

  public List<Guitar> search(Guitar searchGuitar) {
    List matchingGuitars = new ArrayList();
    for (Iterator i = guitars.iterator(); i.hasNext();) {
      Guitar guitar = (Guitar)i.next();
      if (searchGuitar.getBuilder() != guitar.getBuilder()) {
        continue;
      }
      if (searchGuitar.getModel() != guitar.getModel()) {
        continue;
      }
      if (searchGuitar.getType() != guitar.getType()) {
        continue;
      }
      if (searchGuitar.getBackWood() != guitar.getBackWood()) {
        continue;
      }
      if (searchGuitar.getTopWood() != guitar.getTopWood()) {
        continue;
      }
      matchingGuitars.add(guitar);
    }
    return matchingGuitars;
  }
}
