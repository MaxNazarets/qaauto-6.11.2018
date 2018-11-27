import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//button[@data-control-name='nav.settings']")
    private WebElement meButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return welcomeMessage.isDisplayed()
                && driver.getTitle().contains("LinkedIn")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/feed/");
    }

    public String acceptWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public String acceptProfileName() {
        return driver.findElement(By.xpath("//div[@class='nav-settings__member-info-container']/h3")).getText();
    }

    public void selectMeButton() {meButton.click();}
}

