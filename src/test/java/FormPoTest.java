import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;

import java.io.File;

public class FormPoTest extends TestBase {

    @Test
    public void newFormTest() {
        File file = new File("src\\main\\resources\\emptyFile.txt");
        driver.get("http://seleniumui.tc-sii.com/form.php");
        FormPage formPage = new FormPage(driver);
        formPage.setFirstName("jan")
                .setLastName("kowalski")
                .setEmail("jan@wp.pl")
                .selectSex("male")
                .setAge("15")
                .randomYearsOfExperience()
                .selectManualTesterProfession()
                .selectRandomContinent()
                .selectSeleniumCommand("switch-commands")
                .addFile(file.getAbsolutePath())
                .submit();
        Assert.assertEquals(formPage.getMessage(), "Form send with success");
    }
}
