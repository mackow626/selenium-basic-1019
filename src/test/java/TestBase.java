import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.Random;

public class TestBase {
    // driver który będziemy używać w testach
    WebDriver driver;

    /*  metoda odpowiadająca za inicjalizacje drivera wraz z wstępnymi ustawieniami
        przed każdym testam
        plik chromedrivera znajduje się w projekcie
        dlatego możemy podać jego relatywną ścieżkę (względem projektu)
        dzięki temu testy będą działać na waszych komputerach bez względu na to
        w jakim folderze znajduje się projekt
     */
    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        driver = new ChromeDriver(options);
    }

    // zamykanie przeglądarki i drivera po każdym teście
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // metoda zwracająca losowy WebElement z listy
    // musimy wybierać losowy numer z zakresu od 0 do rozmiaru tablicy-1
    // dlatego że tablicę numerujemy od 0. Wiec tablica o indeksach 0 1 2 3,
    // ma 4 elementy ale maksymalny indeks to 3
    WebElement getRandomElement(List<WebElement> elements) {
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(elements.size() - 1);
        return elements.get(randomNumber);
    }


    // bardzo zły sleep, tylko i wyłącznie na potrzeby początkowych testów
    void veryBadSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
