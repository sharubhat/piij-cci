package java8.ooad.appendix;

public class Jet extends Airplane {
  private static final int MULTIPLIER = 2;

  public void setSpeed(int speed) {
    super.setSpeed(speed * MULTIPLIER);
  }

  public void accelerate() {
    super.setSpeed(getSpeed() * 2);
  }
}
