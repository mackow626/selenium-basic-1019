import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CalendarTest extends TestBase {
    List<String> allMonths =
            Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @Test
    public void Test() {
        driver.get("http://seleniumui.tc-sii.com/datepicker.php");
        moveTo("10", "October", 2019);
        Assert.assertEquals(getSelectedDate(), "10/10/2019");

        moveTo("10", "December", 2019);
        Assert.assertEquals(getSelectedDate(), "12/10/2019");

        moveTo("10", "April", 2020);
        Assert.assertEquals(getSelectedDate(), "04/10/2020");

        moveTo("27", "January", 2020);
        Assert.assertEquals(getSelectedDate(), "01/27/2020");

        moveTo("12", "December", 2019);
        Assert.assertEquals(getSelectedDate(), "12/12/2020");
    }


    private void moveTo(String expectedDay, String expectedMonth, int expectedYear) {
        WebElement dataPicker = driver.findElement(By.id("datepicker"));
        dataPicker.click();
        sleep();

        if (expectedYear < getYear()) {
            goPrev(expectedMonth, expectedYear);
        } else if (expectedYear > getYear()) {
            goNext(expectedMonth, expectedYear);
        } else if (getIndexOfMonth(expectedMonth) < getIndexOfMonth(getMonth())) {
            goPrev(expectedMonth, expectedYear);
        } else if (getIndexOfMonth(expectedMonth) > getIndexOfMonth(getMonth())) {
            goNext(expectedMonth, expectedYear);
        }
        selectDay(expectedDay);
    }

    private String getSelectedDate() {
        return driver.findElement(By.id("datepicker")).getAttribute("value");
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

    public void goNext(String expectedMonth, int expectedYear) {
        while (!expectedMonth.equals(getMonth()) || expectedYear != getYear()) {
            driver.findElement(By.className("ui-datepicker-next")).click();
            sleep();
        }
    }

    public void goPrev(String expectedMonth, int expectedYear) {
        while (!expectedMonth.equals(getMonth()) || expectedYear != getYear()) {
            driver.findElement(By.className("ui-datepicker-prev")).click();
            sleep();
        }
    }


    private String getMonth() {
        return driver.findElement(By.className("ui-datepicker-month")).getText();
    }

    private int getYear() {
        return Integer.parseInt(driver.findElement(By.className("ui-datepicker-year")).getText());
    }

    private int getIndexOfMonth(String monthName) {
        return allMonths.indexOf(monthName);
    }
}
