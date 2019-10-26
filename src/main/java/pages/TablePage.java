package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TablePage {
    public TablePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> allPeaks;

    public List<RowPage> getAllPeaks() {
        List<RowPage> allPeaksPo = new ArrayList<>();
        for (WebElement allPeaksElement : allPeaks) {
            allPeaksPo.add(new RowPage(allPeaksElement));
        }
        return allPeaksPo;
    }
}
