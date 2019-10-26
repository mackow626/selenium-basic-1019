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
    }
}
