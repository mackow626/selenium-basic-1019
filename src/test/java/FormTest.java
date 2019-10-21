import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class FormTest extends TestBase {
    /*
            Flow obsługi elementu typu select:
            1. Znajdź WebElement odpowiadający na stronie za selecta
            2. Stwórz obiekt typu select za pomocą WebElemebtu z pkt 1
            3. Obiekt selekt ma wszystkie metody odpowiedzialne za wybiera różnych opcji
                pobieranie dostępnych opcji, oraz wiele innych przydatnych metod

           =============================================

            Flow obsługi uploadu pliku:
            1. Znajdz WebElement odpowiadający na stronie za 'input' gdzie wpisana jest ścieżka do pliku
            2. Do projektu dodaj plik który będziesz używać w tym teście
            3. Wyślij ABSOLUTNĄ ścieżkę pliku do znalezionego WebElementu w pkt 1 (relatywna tutaj nie zadziała)

            * jeżeli chcesz posłużyć się ścieżką relatywną aby test działał niezależnie od tego w jakim
            * folderze jest plik, możesz posłużyć się klasą "File", stwórz obiekt klasy File i przekaż
            * do niego relatywną ścieżkę pliku. Dzięki temu możesz skorzystać z metody file.getAbsolutePath()
            * ta metoda zawarta w klasie File sama wygeneruje ścieżkę absolutną do pliku

            przykład ścieżki relatywnej: src\main\resources\temptyFile.txt
            przykład ścieżki absolutnej: C:\projects\sii\selenium-basic-1019\src\main\resources\temptyFile.txt
    */

    @Test
    public void formTest() {
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

        // wybieranie losowego elementu z List<WebElement> years, a następnie kliknięcie w niego
        // wszystko po to aby za każdym uruchomieniem test wybierał inną ilość lat doświadczenia
        List<WebElement> years = driver.findElements(By.name("gridRadiosExperience"));
        WebElement randomYear = getRandomElement(years);
        randomYear.click();

        WebElement automationTester = driver.findElement(By.id("gridCheckAutomationTester"));
        automationTester.click();

        // flow selecta
        WebElement commandsElement = driver.findElement(By.id("selectSeleniumCommands"));
        Select commandsSelect = new Select(commandsElement);
        commandsSelect.selectByValue("switch-commands");

        // flow selecta z wybieraniem losowej opcji
        // pobieram z selecta wszystkie dostępne do wyboru opcje a następnie losuję jedną z nich
        // na koniec wybieram z selecta opcję którą wylosowałem, używając wybierania po tekście opcji
        Select continentsSelect = new Select(driver.findElement(By.id("selectContinents")));
        List<WebElement> allOptions = continentsSelect.getOptions();
        WebElement randomOption = getRandomElement(allOptions);
        continentsSelect.selectByVisibleText(randomOption.getText());

        // flow uploadu pliku
        WebElement fileInput = driver.findElement(By.id("chooseFile"));
        File file = new File("src\\main\\resources\\emptyFile.txt");
        fileInput.sendKeys(file.getAbsolutePath());

        WebElement signIn = driver.findElement(By.cssSelector("button[type='submit']"));
        signIn.click();

        String validatorMessage = driver.findElement(By.id("validator-message")).getText();

        // sprawdzamy przy pomocy asercji czy została wyświetlona odpowiednia wiadomość validacji
        Assert.assertEquals(validatorMessage, "Form send with success");
    }
}
