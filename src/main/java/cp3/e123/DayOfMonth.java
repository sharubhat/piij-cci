package cp3.e123;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Exercise 1.2.3-3 from book competitive programming 3
 *
 * Given a date, determine the day of the week (Monday, . . . , Sunday) on that day.
 * (e.g. 9 August 2010—the launch date of the first edition of this book—is a Monday.)
 */
public class DayOfMonth {
    public static void main(String[] args) {
        String date = "09 August 2010";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        System.out.println(localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));
    }
}
