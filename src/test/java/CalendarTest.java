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
        moveTo("10", "October", "2019");
    }

    private void moveTo(String day, String month, String year) {
        WebElement dataPicker = driver.findElement(By.id("datepicker"));
        dataPicker.click();
        Sleep();
        //TODO przełączenie na odpwiedni rok + miesiąc

        // po wszystkim wybranie dnia
        selectDay(day);
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

    private String getMonth() {
        return driver.findElement(By.className("ui-datepicker-month")).getText();
    }

    private String getYear() {
        return driver.findElement(By.className("ui-datepicker-year")).getText();
    }
}
