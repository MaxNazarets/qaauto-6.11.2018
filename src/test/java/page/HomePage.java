package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//button[@data-control-name='nav.settings']")
    private WebElement meButton;

    @FindBy(xpath = "//div[@class='nav-search-typeahead']//input")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return welcomeMessage.isDisplayed()
                && driver.getTitle().contains("LinkedIn")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/feed/");
    }

    public String acceptProfileName() {
        return driver.findElement(By.xpath("//div[@class='nav-settings__member-info-container']/h3")).getText();
    }

    public void selectMeButton() {meButton.click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SearchResultsPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchResultsPage(driver);
    }
}

