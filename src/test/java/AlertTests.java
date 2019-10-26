import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertTests extends TestBase {

    @BeforeMethod
    public void openAlertPage() {
        driver.navigate().to(" http://seleniumui.tc-sii.com/alerts.php");
    }

    @Test
    public void simpleAlert() {
        driver.findElement(By.id("simple-alert")).click();
        driver.switchTo().alert().accept();
        String validationMessage = driver.findElement(By.id("simple-alert-label")).getText();
        Assert.assertEquals(validationMessage, "OK button pressed");
    }

    @Test
    public void promptAlert() {
        driver.findElement(By.id("prompt-alert")).click();
        driver.switchTo().alert().sendKeys("Lord Vader");
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.id("prompt-label"))
                .getText(), "Hello Lord Vader! How are you today?");
    }

    @Test
    public void confirmAlert() {
        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.id("confirm-label")).getText(), "You pressed OK!");
        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().dismiss();
        Assert.assertEquals(driver.findElement(By.id("confirm-label")).getText(), "You pressed Cancel!");
    }
}
