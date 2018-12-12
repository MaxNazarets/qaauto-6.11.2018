package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for SubmitResetPasswordPage.
 */
public class SubmitResetPasswordPage extends BasePage {

    /**
     * Constructor of SubmitResetPasswordPage class.
     *
     * @param driver - webDriver instance from Test.
     */
    public SubmitResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;


    /**
     * Method to check if page is loaded.
     *
     * @return true/false
     */
    public boolean isPageLoaded() {
        return resendLinkButton.isDisplayed()
                && driver.getTitle().contains("Please check your mail for reset password link")
                && driver.getCurrentUrl().contains("/request-password-reset-submit");
    }

    /**
     * Method that navigate to Link from Email.
     *
     * @return new SetNewPasswordPage object.
     */
    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "max.nazarets.tst@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String resetPasswordLink = StringUtils.substringBetween(message, "line-height:1.25;\"><a href=\"", "\" style").replace("amp;", "");
        driver.navigate().to(resetPasswordLink);

        return new SetNewPasswordPage(driver);

    }
}
