import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class TableTest extends TestBase {
    /*
    Test steps:

    Get all rows from table to List
    Print out mountain names that are located in  "Switzerland" and are higher than 4000 m
    */
    @Test
    public void tableTest() {
        driver.get("http://seleniumui.tc-sii.com/table.php");

    }
}
