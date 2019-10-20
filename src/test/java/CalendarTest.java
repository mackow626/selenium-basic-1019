import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CalendarTest extends TestBase {
    List<String> allMonths = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @Test
    public void Test() {
        driver.get("http://seleniumui.tc-sii.com/datepicker.php");
        moveTo("10", "December", 2019);
    }

    private void moveTo(String expectedDay, String expectedMonth, int expectedYear) {
        WebElement dataPicker = driver.findElement(By.id("datepicker"));
        dataPicker.click();
        sleep();
        //TODO przełączenie na odpwiedni rok + miesiąc

        //jeżeli (oczeRok<aktRok) => prev
        if (expectedYear < getYear()) {
            goPrev();
        }
        //jeżeli (oczeRok>aktRok) => next
        else if (expectedYear > getYear()) {
            goNext();
        }
        //jeżeli (oczeMsc<aktMsc)=> prev
        else if (getIndexOfMonth(expectedMonth) < getIndexOfMonth(getMonth())) {
            goPrev();
        }
        //jeżeli (oczeMsc>aktMsc)=> next
        else if (getIndexOfMonth(expectedMonth) > getIndexOfMonth(getMonth())) {
            goNext();
        }

        // po wszystkim wybranie dnia
        selectDay(expectedDay);
    }

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

    private String getCurrentMonthNumber() {
        return driver.findElement(By.xpath("//td[.='15']")).getAttribute("data-month");
    }

    public void goNext() {
        driver.findElement(By.className("ui-datepicker-next")).click();
        sleep();
    }

    public void goPrev() {
        driver.findElement(By.className("ui-datepicker-prev")).click();
        sleep();
    }


    private String getMonth() {
        return driver.findElement(By.className("ui-datepicker-month")).getText();
    }

    private int getYear() {
        return Integer.parseInt(driver.findElement(By.className("ui-datepicker-year")).getText());
    }

    private int getIndexOfMonth(String monthName){
        return allMonths.indexOf(monthName);
    }
}
