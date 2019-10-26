import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DroppableTest extends TestBase {

    /*
    Test steps:

       drag 'Drag me to my target' square and drop it in 'Drop here' area
       check if "Dropped!" text appeared

    */

    @BeforeMethod
    public void openDroppabelPage() {
        driver.get("http://seleniumui.tc-sii.com//droppable.php");

    }

    @Test
    public void dragAndDropTest() {
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(drag, drop)
                .perform();
        Assert.assertEquals(drop.getText(), "Dropped!");
    }

    @Test
    public void holdAndDropTest() {
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(drag)
                .release(drop)
                .perform();
        Assert.assertEquals(drop.getText(), "Dropped!");
    }

    @Test
    public void holdMoveAndDropTest() {
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);

        actions.clickAndHold(drag)
                .moveToElement(drop)
                .release()
                .perform();
        Assert.assertEquals(drop.getText(), "Dropped!");
    }

    @Test
    public void dragAndDropByTest() {
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);

        int x1 = drag.getLocation().getX();
        int y1 = drag.getLocation().getY();

        int x2 = drop.getLocation().getX();
        int y2 = drop.getLocation().getY();

        actions.dragAndDropBy(drag, x2 - x1, y2 - y1)
                .perform();
        Assert.assertEquals(drop.getText(), "Dropped!");
    }
}