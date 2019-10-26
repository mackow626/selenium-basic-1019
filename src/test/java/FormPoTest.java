import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;

import java.io.File;

public class FormPoTest extends TestBase {

    @Test
    public void newFormTest(){
        File file = new File("src\\main\\resources\\emptyFile.txt");
        driver.get("http://seleniumui.tc-sii.com/form.php");
        FormPage formPage = new FormPage(driver);
        formPage.setFirstName("jan");
        formPage.setLastName("kowalski");
        formPage.setEmail("jan@wp.pl");
        formPage.selectSex("male");
        formPage.setAge("15");
        formPage.randomYearsOfExperience();
        formPage.selectManualTesterProfession();
        formPage.selectRandomContinent();
        formPage.selectSeleniumCommand("switch-commands");
        formPage.addFile(file.getAbsolutePath());
        formPage.submit();
        Assert.assertEquals(formPage.getMessage(), "Form send with success");
    }
}
