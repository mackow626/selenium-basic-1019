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

    public FormPage setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public FormPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public FormPage setEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public FormPage selectSex(int index) {
        sexRadioButtons.get(index).click();
        return this;
    }

    public FormPage selectSex(String sexName) {
        for (WebElement sex : sexRadioButtons) {
            if (sex.getAttribute("value").equals(sexName)) {
                sex.click();
            }
        }
        return this;
    }


    public FormPage setAge(String age) {
        this.age.sendKeys(age);
        return this;
    }

    public FormPage randomYearsOfExperience() {
        getRandomElement(yearsOfExperience).click();
        return this;
    }

    public FormPage selectManualTesterProfession() {
        manualTesterProfession.click();
        return this;
    }

    public FormPage selectRandomContinent() {
        Select continentsSelect = new Select(continents);
        WebElement randomOption = getRandomElement(continentsSelect.getOptions());
        selectContinent(randomOption.getText());
        return this;
    }

    public FormPage selectContinent(String continent) {
        new Select(continents).selectByVisibleText(continent);
        return this;
    }

    public FormPage selectSeleniumCommand(String command) {
        new Select(seleniumCommands).selectByValue(command);
        return this;
    }

    public FormPage addFile(String filePath) {
        fileInput.sendKeys(filePath);
        return this;
    }


    public FormPage submit() {
        submit.click();
        return this;
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
