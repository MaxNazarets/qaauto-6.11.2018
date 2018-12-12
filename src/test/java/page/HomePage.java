package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for HomePage.
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//button[@data-control-name='nav.settings']")
    private WebElement meButton;

    @FindBy(xpath = "//div[@class='nav-search-typeahead']//input")
    private WebElement searchField;

    /**
     * Constructor of HomePage class.
     * @param driver - webDriver instance from Test.
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to check if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return welcomeMessage.isDisplayed()
                && driver.getTitle().contains("LinkedIn")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/feed/");
    }

    /**
     * Method that getText of ProfileName element.
     * @return String value of element
     */
    public String acceptProfileName() {
        return driver.findElement(By.xpath("//div[@class='nav-settings__member-info-container']/h3")).getText();
    }

    /**
     * Method that click on "Me" button in toolbar.
     */
    public void selectMeButton() {meButton.click();
    waitUntilElementIsVisible(meButton);
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Method that fill the searchField and press "ENTER".
     * @param searchTerm - String value searchTerm.
     * @return new SearchResultsPage object.
     */
    public SearchResultsPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}

