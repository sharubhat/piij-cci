package java8.ooad.chapter1;

public enum Type {
  ACOUSTIC, ELECTRIC;

  @Override
  public String toString() {
    switch(this) {
      case ACOUSTIC: return "acoustic";
      case ELECTRIC: return "electric";
      default: return "";
    }
  }
}
