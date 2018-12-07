package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitResetPasswordPage extends BasePage{

    public SubmitResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;


    public boolean isPageLoaded() {
        return resendLinkButton.isDisplayed()
                && driver.getTitle().contains("Please check your mail for reset password link")
                && driver.getCurrentUrl().contains("/request-password-reset-submit");
    }

    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "max.nazarets.tst@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        return new SetNewPasswordPage(driver);

    }
}
