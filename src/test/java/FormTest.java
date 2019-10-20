import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormTest extends TestBase{

    @Test
    public void Test() {
        driver.get("http://seleniumui.tc-sii.com/form.php");

        WebElement firstName = driver.findElement(By.id("inputFirstName3"));
        firstName.sendKeys("Jan");

        WebElement lastName = driver.findElement(By.id("inputLastName3"));
        lastName.sendKeys("Kowalski");

        WebElement email = driver.findElement(By.id("inputEmail3"));
        email.sendKeys("Jan@wp.pl");

        WebElement male = driver.findElement(By.cssSelector("input[value='male']"));
        male.click();

        WebElement age = driver.findElement(By.id("inputAge3"));
        age.sendKeys("15");

        List<WebElement> years = driver.findElements(By.name("gridRadiosExperience"));
        WebElement randomYear = getRandomElement(years);
        randomYear.click();

        WebElement automationTester = driver.findElement(By.id("gridCheckAutomationTester"));
        automationTester.click();

        // znajdz element selecta
        WebElement continentsElement = driver.findElement(By.id("selectContinents"));
        // stwórz obiekt typu Select
        Select continentsSelect = new Select(continentsElement);
        // pobierz wszystkie dostępne opcje selektu
        List<WebElement> allOptions = continentsSelect.getOptions();
        // wybierz losową opcję z listy
        WebElement randomOption = getRandomElement(allOptions);
        // wybierz element z selecta po tekście
        // jako text podajemy text z losowe wybranej opcji_
        continentsSelect.selectByVisibleText(randomOption.getText());


        WebElement commandsElement = driver.findElement(By.id("selectSeleniumCommands"));
        Select commandsSelect = new Select(commandsElement);
        commandsSelect.selectByValue("switch-commands");

        WebElement fileInput = driver.findElement(By.id("chooseFile"));

        File file = new File("src\\main\\resources\\emptyFile.txt");
        fileInput.sendKeys(file.getAbsolutePath());

        WebElement signIn = driver.findElement(By.cssSelector("button[type='submit']"));
        signIn.click();

        String messageText = driver.findElement(By.id("validator-message")).getText();
        Assert.assertEquals(messageText, "Form send with success");
    }


    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
