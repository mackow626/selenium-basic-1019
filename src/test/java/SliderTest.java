import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class SliderTest extends TestBase {

    private int getActualSliderValue(){
        return Integer.parseInt(
                driver.findElement(By.id("custom-handle")).getText());
    }

    private void moveSlider(int number, Keys key){
        while (getActualSliderValue() != number){
            driver.findElement(By.id("custom-handle")).sendKeys(key);
        }
    }

    public void moveTo(int number) {
        int actualSliderValue = getActualSliderValue();
        if (actualSliderValue > number) {
            moveSlider(number,Keys.ARROW_LEFT);
        } else if (actualSliderValue < number) {
            moveSlider(number,Keys.ARROW_RIGHT);
        }
    }

    @Test
    public void Test() {
        driver.get("http://seleniumui.tc-sii.com/slider.php");
        moveTo(50);
        moveTo(20);
        moveTo(20);
        moveTo(75);
    }
}
