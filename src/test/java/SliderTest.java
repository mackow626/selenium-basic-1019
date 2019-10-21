import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class SliderTest extends TestBase {

    @Test
    public void Test() {
        driver.get("http://seleniumui.tc-sii.com/slider.php");
        moveTo(50);
        moveTo(20);
        moveTo(20);
        moveTo(75);
    }

    // metoda spradzająca w którą stronę ma się przesunąć slider a następnie zaczynająca go przesuwać
    private void moveTo(int expectedSliderValue) {
        int actualSliderValue = getSliderValue();

        if (actualSliderValue > expectedSliderValue) {
            moveSlider(expectedSliderValue, Keys.ARROW_LEFT);
        } else if (actualSliderValue < expectedSliderValue) {
            moveSlider(expectedSliderValue, Keys.ARROW_RIGHT);
        }
    }

    // wysyłanie do slidera strzałkę w lewo / prawo aby go przesunąć
    private void moveSlider(int expectedSliderValue, Keys key) {
        while (getSliderValue() != expectedSliderValue) {
            driver.findElement(By.id("custom-handle")).sendKeys(key);
        }
    }

    // pobieranie textu wyświetlanego w sliderze i parsowanie go do inta
    private int getSliderValue() {
        return Integer.parseInt(
                driver.findElement(By.id("custom-handle")).getText());
    }
}
