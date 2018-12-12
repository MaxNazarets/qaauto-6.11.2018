package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

/**
 * PageObject class for RequestResetPasswordPage.
 */
public class RequestResetPasswordPage extends BasePage {

    /**
     * Constructor of RequestResetPasswordPage class.
     *
     * @param driver - webDriver instance from Test.
     */
    public RequestResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;


    /**
     * Method to check if page is loaded.
     *
     * @return true/false
     */
    public boolean isPageLoaded() {
        return findAccountButton.isDisplayed()
                && driver.getTitle().contains("Reset Password | LinkedIn")
                && driver.getCurrentUrl().contains("/uas/request-password-reset");
    }

    /**
     * Method that connect to GmailService and click on 'findAccount' button.
     *
     * @param userEmail - input text in field.
     * @return new SubmitResetPasswordPage object.
     */
    public SubmitResetPasswordPage findAccount(String userEmail) {
        gMailService = new GMailService();
        gMailService.connect();

        emailField.sendKeys(userEmail);
        findAccountButton.click();
        return new SubmitResetPasswordPage(driver);
    }
}
