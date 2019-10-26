import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DroppableTest extends TestBase {

    /*
    Test steps:

       drag 'Drag me to my target' square and drop it in 'Drop here' area
       check if "Dropped!" text appeared

    */

    public void droppableTest(){
        driver.get("http://seleniumui.tc-sii.com/draggable.php");
    }

}