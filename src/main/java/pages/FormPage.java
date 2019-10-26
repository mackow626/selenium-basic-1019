package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FormPage {
    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "inputFirstName3")
    private WebElement firstName;

    @FindBy(name = "gridRadiosSex")
    private List<WebElement> sexRadioButtons;

    public void setFirstName(String name) {
        firstName.sendKeys(name);
    }
}
