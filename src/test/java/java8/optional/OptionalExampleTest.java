package java8.optional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * ref : http://www.baeldung.com/java-optional
 */
public class OptionalExampleTest {
  @Test
  public void whenCreatesEmptyOptional_thenCorrect() {
    Optional<String> empty = Optional.empty();
    assertFalse(empty.isPresent());
  }

  @Test
  public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
    String name = "Name";
    // creating new optional with help of static 'of' api.
    Optional<String> nonEmpty = Optional.of(name);
    assertTrue(nonEmpty.isPresent());
  }

  @Test
  public void givenNonNull_whenCreatesOptional_thenCorrect() {
    String name = "Name";
    Optional<String> optional = Optional.of(name);
    assertEquals("Optional[Name]", optional.toString());
  }

  @Test(expected = NullPointerException.class)
  public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
    String name = null;
    Optional<String> optional = Optional.of(name);
  }

  @Test(expected = NoSuchElementException.class)
  public void givenNonNull_whenCreatesNullable_thenCorrect() {
    String name = null;
    Optional<String> optional = Optional.ofNullable(name);
    assertEquals("Optional.empty", optional.toString());
    assertNull(optional.get());
  }

  @Test
  public void givenOptional_whenIsPresentWorks_thenCorrect() {
    Optional<String> optional = Optional.ofNullable("Name");
    assertTrue(optional.isPresent());

    optional = Optional.ofNullable(null);
    assertFalse(optional.isPresent());
  }

  @Test
  public void givenOptional_whenIfPresentWorks_thenCorrect() {
    // old way
    String word = null;
    if (word != null) {
      System.out.println(word.length());
    }
    // optional way
    Optional<String> optional = Optional.ofNullable(null);
    optional.ifPresent(name -> System.out.println(name.length()));
  }

  @Test
  public void givenOptional_whenOrElseWorks_thenCorrect() {
    String nullName = null;
    String name = Optional.ofNullable(nullName).orElse("Name");
    assertEquals("Name", name);
  }

  @Test
  public void givenOptional_whenOrElseGetWorks_thenCorrect() {
    String nullName = null;
    String name = Optional.ofNullable(nullName).orElseGet(() -> "Name");
    assertEquals("Name", name);
  }

  public String getMyDefault() {
    System.out.println("Getting default value");
    return "Default Value";
  }

  @Test
  public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
    String text = null;

    System.out.println("Using orElseGet:");
    String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
    assertEquals("Default Value", defaultText);

    System.out.println("Using orElse");
    defaultText = Optional.ofNullable(text).orElse(getMyDefault());
    assertEquals("Default Value", defaultText);
  }

  @Test
  public void whenOrElseAndOrElseGetDiffer_thenCorrect() {
    String text = "Actual text";

    // getMyDefault is not invoked by orElseGet if optional is present.
    // this is important difference. if making an expensive object creation or call
    // orElseGet performs better
    System.out.println("Using orElseGet");
    String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
    assertEquals("Actual text", defaultText);

    System.out.println("Using orElse");
    defaultText = Optional.ofNullable("Actual text").orElse(getMyDefault());
    assertEquals("Actual text", defaultText);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenOrElseThrowWorks_thenCorrect() {
    String nullName = null;
    String name = Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
  }

  @Test
  public void givenOptional_whenGetsValue_thenCorrect() {
    Optional<String> optional =  Optional.of("Name");
    String name = optional.get();
    assertEquals("Name", name);
  }

  @Test(expected = NullPointerException.class)
  public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
    Optional<String> optional = Optional.of(null);
    String name = optional.get();
  }

  @Test
  public void whenOptionalFilterWorks_thenCorrect() {
    Integer year = 2016;
    Optional<Integer> yearOptional = Optional.of(year);
    boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
    assertTrue(is2016);
    boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
    assertFalse(is2017);
  }

  public class Modem {
    private Double price;

    public Modem(Double price) {
      this.price = price;
    }

    public Double getPrice() {
      return price;
    }

    public void setPrice(Double price) {
      this.price = price;
    }
  }

  // old way of null check
  public boolean pricesInRange(Modem modem) {
    return modem != null && modem.getPrice() != null
        && (modem.getPrice() >= 10 && modem.getPrice() <= 15);
  }

  // using optional filter api
  public boolean pricesInRange2(Modem modem) {
    return Optional.ofNullable(modem)
        .map(Modem::getPrice)
        .filter(p -> p >= 10 && p <= 15)
        .isPresent();
  }

  @Test
  public void whenFiltersWithOptional_thenCorrect() {
    assertTrue(pricesInRange(new Modem(10.0)));
    assertFalse(pricesInRange(new Modem(9.9)));
    assertFalse(pricesInRange(new Modem(null)));
    assertFalse(pricesInRange(new Modem(15.5)));
    assertFalse(pricesInRange(null));

    assertTrue(pricesInRange2(new Modem(10.0)));
    assertFalse(pricesInRange2(new Modem(9.9)));
    assertFalse(pricesInRange2(new Modem(null)));
    assertFalse(pricesInRange2(new Modem(15.5)));
    assertFalse(pricesInRange2(null));
  }

  @Test
  public void givenOptional_whenMapWorks_thenCorrect() {
    List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
    Optional<List<String>> listOptional = Optional.of(companyNames);

    int size = listOptional
        .map(List::size)
        .orElse(0);
    assertEquals(6, size);
  }

  public class Person {
    private String name;
    private int age;
    private String password;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public Optional<String> getName() {
      return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
      return Optional.ofNullable(age);
    }

    public Optional<String> getPassword() {
      return Optional.ofNullable(password);
    }
  }

  @Test
  public void givenOptional_whenFlatMapWorks_thenCorrect() {
    Person person = new Person("John", 28);
    Optional<Person> personOptional = Optional.of(person);

    Optional<Optional<String>> nameOptionalWrapper =
        personOptional.map(Person::getName);
    Optional<String> nameOptional =
        nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
    String name = nameOptional.orElse("");
    assertEquals("John", name);

    name = personOptional
        .flatMap(Person::getName)
        .orElse("");
    assertEquals("John", name);
  }
}