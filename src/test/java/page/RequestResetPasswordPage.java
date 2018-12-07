package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class RequestResetPasswordPage extends BasePage{

    public RequestResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;


    public boolean isPageLoaded() {
        return findAccountButton.isDisplayed()
                && driver.getTitle().contains("Reset Password | LinkedIn")
                && driver.getCurrentUrl().contains("/uas/request-password-reset");
    }

    public SubmitResetPasswordPage findAccount(String userEmail) {
        gMailService = new GMailService();
        gMailService.connect();

        emailField.sendKeys(userEmail);
        findAccountButton.click();
        return new SubmitResetPasswordPage(driver);
    }
}
