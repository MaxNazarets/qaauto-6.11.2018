package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for SuccessResetPasswordPage.
 */
public class SuccessResetPasswordPage extends BasePage{

    /**
     * Constructor of SuccessResetPasswordPage class.
     * @param driver - webDriver instance from Test.
     */
    public SuccessResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;


    /**
     * Method to check if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return goToHomepageButton.isDisplayed()
                && driver.getTitle().contains("You've successfully reset your password.");
    }

    /**
     * Method that click on 'GoToHomePage' button.
     * @return new HomePage object.
     */
    public HomePage clickGoToHomepageButton() {
        goToHomepageButton.click();
        return new HomePage(driver);
    }
}
