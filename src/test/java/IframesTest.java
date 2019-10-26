import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class IframesTest extends TestBase {
    /*
    Test steps:

    Switch to first iframe and fill form
    Switch to second iframe and fill form
    Switch to main frame and click on 'Basic' button in main menu
    */
    @Test
    public void iframeTest() {
        driver.get("http://seleniumui.tc-sii.com/iframes.php");

        // 1st frame
        driver.switchTo().frame("iframe1");
        driver.findElement(By.id("inputFirstName3")).sendKeys("Jan");
        driver.findElement(By.id("inputSurname3")).sendKeys("Jankowski");
        // 2nd frame
        driver.switchTo().defaultContent();
        driver.switchTo().frame("iframe2");

        driver.findElement(By.id("inputLogin")).sendKeys("janjan");
        driver.findElement(By.id("inputPassword")).sendKeys("12345");
        new Select(driver.findElement(By.id("inlineFormCustomSelectPref"))).selectByIndex(1);
        driver.findElement(By.id("gridRadios2")).click();
        // parent frame
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//a[contains(text(),'Basic')]")).click();
    }
}
