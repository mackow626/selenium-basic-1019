import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
    Tutaj przykład bardziej zaawansowanej implementacji testu kalendarza ,który wam obiecałem
    (ale nadal bez użycia Page Obcjet Pattern). Omówię wam poniższy test na szybko na początku kolejnych zajęć
 */
public class CalendarAdvancedTest extends TestBase {
    @DataProvider()
    public Object[][] dates() {
        return new Object[][]{
                {new CustomDate("27", "October", "2019")},
                {new CustomDate("1", "December", "2019")},
                {new CustomDate("3", "April", "2020")},
                {new CustomDate("15", "January", "2020")},
                {new CustomDate("13", "December", "2019")}};
    }

    @Test(dataProvider = "dates")
    public void calendarAdvancedTest(CustomDate expectedDate) {
        driver.get("http://seleniumui.tc-sii.com/datepicker.php");
        selectDate(expectedDate);
        Assert.assertEquals(getSelectedDate(), expectedDate.toAmerican());
    }

    private void selectDate(CustomDate expectedDate) {
        driver.findElement(By.id("datepicker")).click();

        if (expectedDate.isAfter(getYear(), getMonth())) {
            goToYearMonth(expectedDate, Direction.next);
        } else if (expectedDate.isBefore(getYear(), getMonth())) {
            goToYearMonth(expectedDate, Direction.prev);
        }

        selectDay(expectedDate.getDay());
    }

    private String getSelectedDate() {
        return driver.findElement(By.id("datepicker")).getAttribute("value");
    }

    private void goToYearMonth(CustomDate expectedDate, Direction direction) {
        while (!getYearMonth().equals(expectedDate.getYearMonth())) {
            driver.findElement(By.className("ui-datepicker-" + direction)).click();
        }
    }

    private void selectDay(String dayToSelect) {
        driver.findElements(By.cssSelector("td[data-month='" + getCurrentMonthNumber() + "']"))
                .stream().filter(day -> day.getText().equals(dayToSelect)).findFirst().ifPresent(WebElement::click);
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

    private String getYearMonth() {
        return driver.findElement(By.className("ui-datepicker-title")).getText();
    }

    private enum Direction {
        next, prev
    }
}
