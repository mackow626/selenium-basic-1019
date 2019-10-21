import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

class CustomDate {
    private LocalDate date;

    CustomDate(String day, String month, String year) {
        date = LocalDate.of(Integer.parseInt(year), Month.valueOf(month.toUpperCase()), Integer.parseInt(day));
    }

    boolean isAfter(String year, String month) {
        return toYearMonth(date).isAfter(toYearMonth(year, month));
    }

    boolean isBefore(String year, String month) {
        return toYearMonth(date).isBefore(toYearMonth(year, month));
    }

    private LocalDate toYearMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    private LocalDate toYearMonth(String year, String month) {
        return LocalDate.of(Integer.parseInt(year), Month.valueOf(month.toUpperCase()), 1);
    }

    String toAmerican() {
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    String getYearMonth() {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + date.getYear();
    }

    String getDay() {
        return String.valueOf(date.getDayOfMonth());
    }
}