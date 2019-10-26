package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class FormPage {
    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "inputFirstName3")
    private WebElement firstName;

    @FindBy(id = "inputLastName3")
    private WebElement lastName;

    @FindBy(id = "inputEmail3")
    private WebElement email;

    @FindBy(name = "gridRadiosSex")
    private List<WebElement> sexRadioButtons;

    @FindBy(id = "inputAge3")
    private WebElement age;

    @FindBy(name = "gridRadiosExperience")
    private List<WebElement> yearsOfExperience;

    @FindBy(id = "gridCheckManulTester")
    private WebElement manualTesterProfession;

    @FindBy(id = "selectContinents")
    private WebElement continents;

    @FindBy(id = "selectSeleniumCommands")
    private WebElement seleniumCommands;

    @FindBy(id = "chooseFile")
    private WebElement fileInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submit;

    @FindBy(id = "validator-message")
    private WebElement message;

    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    public void selectSex(int index) {
        sexRadioButtons.get(index).click();
    }

    public void selectSex(String sexName) {
        for (WebElement sex : sexRadioButtons) {
            if (sex.getAttribute("value").equals(sexName)) {
                sex.click();
            }
        }
    }


    public void setAge(String age) {
        this.age.sendKeys(age);
    }

    public void randomYearsOfExperience() {
        getRandomElement(yearsOfExperience).click();
    }

    public void selectManualTesterProfession() {
        manualTesterProfession.click();
    }

    public void selectRandomContinent() {
        Select continentsSelect = new Select(continents);
        WebElement randomOption = getRandomElement(continentsSelect.getOptions());
        selectContinent(randomOption.getText());
    }

    public void selectContinent(String continent) {
        new Select(continents).selectByVisibleText(continent);
    }

    public void selectSeleniumCommand(String command) {
        new Select(seleniumCommands).selectByValue(command);
    }

    public void addFile(String filePath) {
        fileInput.sendKeys(filePath);
    }


    public void submit() {
        submit.click();
    }

    public String getMessage() {
        return message.getText();
    }

    private WebElement getRandomElement(List<WebElement> elements) {
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(elements.size() - 1);
        return elements.get(randomNumber);
    }

}
