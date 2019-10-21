import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/*
    Wybrana przez was logika rozwiązania w którą stronę przesuwać kalendarz:
    oczekiewanyRok
    oczekiwanyMsc

    akttualnyRok
    aktualnyMsc

    1. jeżeli (oczeRok<aktRok) => prev
    2. jeżeli (oczeRok>aktRok) => next

    jeżeli 2 powyższe warunki nie zostały spełnione to znaczy, że lata są sobie równe więc teraz sprawdzamy miesiące:
        3. jeżeli (oczeMsc<aktMsc)=> prev
        4. jeżeli (oczeMsc>aktMsc)=> next
 */

public class CalendarTest extends TestBase {
    // tablica miesięcy dzięki której możemy porównać który miesiąc jest wcześniejszy/późniejszy
    private List<String> allMonths =
            Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @Test
    public void calendarTest() {
        driver.get("http://seleniumui.tc-sii.com/datepicker.php");
        // wybranie 5 dat oraz sprawdzenie czy odpowiednia data została wybrana
        selectDate("10", "October", 2019);
        Assert.assertEquals(getSelectedDate(), "10/10/2019");

        selectDate("10", "December", 2019);
        Assert.assertEquals(getSelectedDate(), "12/10/2019");

        selectDate("10", "April", 2020);
        Assert.assertEquals(getSelectedDate(), "04/10/2020");

        selectDate("27", "January", 2020);
        Assert.assertEquals(getSelectedDate(), "01/27/2020");

        selectDate("12", "December", 2019);
        Assert.assertEquals(getSelectedDate(), "12/12/2019");
    }


    private void selectDate(String expectedDay, String expectedMonth, int expectedYear) {
        // otworzenie data pickera
        WebElement dataPicker = driver.findElement(By.id("datepicker"));
        dataPicker.click();
        veryBadSleep();

        // implementacja logiki porównywania czy oczekiwany rok+msc jest przed/za aktualnym rok+msc
        if (expectedYear < getYear()) {
            goPrev(expectedMonth, expectedYear);
        } else if (expectedYear > getYear()) {
            goNext(expectedMonth, expectedYear);
        } else if (getNumberOfMonth(expectedMonth) < getNumberOfMonth(getMonth())) {
            goPrev(expectedMonth, expectedYear);
        } else if (getNumberOfMonth(expectedMonth) > getNumberOfMonth(getMonth())) {
            goNext(expectedMonth, expectedYear);
        }

        // na koniec jak już jesteśmy na karcie z odpowiednim miesiącem i rokiem, wybieramy interesujący nas dzień
        selectDay(expectedDay);
    }

    /* pobiera datę która wyświetla się w inpucie np 12/12/2019
       jako że jest to pole typu input to nie zadziała metoda .getText()
       więc musieliśmy użyć metody .getAttribute("value") aby pobrać wartość wyświetloną w inpucie
     */
    private String getSelectedDate() {
        return driver.findElement(By.id("datepicker")).getAttribute("value");
    }


    // metoda która pobiera wszystkie dni z kalendarza do listy
    // ważne aby pobrać tylko dni z aktualnego miesiąca, bo kalendarz wyświetla też kilka dni z pop/nast msc
    // następnie przeszukujemy listę dni aby znaleźć dzień który zawiera w sobie odpowiedni numer i w niego klikamy
    private void selectDay(String dayToSelect) {
        List<WebElement> allDays =
                driver.findElements(By.cssSelector("td[data-month='" + getCurrentMonthNumber() + "']"));
        for (WebElement day : allDays) {
            if (day.getText().equals(dayToSelect)) {
                day.click();
                break;
            }
        }
    }

    // ta metoda pobiera z 15 dnia wartość atrybutu 'data-month', dzięki temu, że 15-ty dzień miesiąca zawsze
    // będzie wyświetlony jako jedyny na karcie kalendarza to pobierając wartość tego atrybutu, będzie znać numer
    // miesiąca. Używamy go później w budowaniu selektora do pobierania dni wyłącznie z aktualnego miesiąca
    private String getCurrentMonthNumber() {
        return driver.findElement(By.xpath("//td[.='15']")).getAttribute("data-month");
    }

    // metoda która przesuwa nam kalendarz do przodu aż zostanie wyświetlony odpowiedni msc + rok
    private void goNext(String expectedMonth, int expectedYear) {
        while (!expectedMonth.equals(getMonth()) || expectedYear != getYear()) {
            driver.findElement(By.className("ui-datepicker-next")).click();
            veryBadSleep();
        }
    }

    // metoda która przesuwa nam kalendarz do tyłu aż zostanie wyświetlony odpowiedni msc + rok
    private void goPrev(String expectedMonth, int expectedYear) {
        while (!expectedMonth.equals(getMonth()) || expectedYear != getYear()) {
            driver.findElement(By.className("ui-datepicker-prev")).click();
            veryBadSleep();
        }
    }


    // pobiera wyświetlany miesiąc w karcie kalendarza
    private String getMonth() {
        return driver.findElement(By.className("ui-datepicker-month")).getText();
    }

    // pobiera wyświetlany rok w karcie kalendarza
    private int getYear() {
        return Integer.parseInt(driver.findElement(By.className("ui-datepicker-year")).getText());
    }

    // zwraca miejsce na którym stoi miesiąc w liście miesięcy, dzięki temu porównamy który miesiąc jest 'większy'
    private int getNumberOfMonth(String monthName) {
        return allMonths.indexOf(monthName);
    }
}
