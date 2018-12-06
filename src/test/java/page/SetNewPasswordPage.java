package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetNewPasswordPage extends BasePage{

    public SetNewPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement retypeNewPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;


    public boolean isPageLoaded() {
        return newPasswordField.isDisplayed()
                && retypeNewPasswordField.isDisplayed()
                && driver.getTitle().contains("Reset Your Password");
    }

    public SuccessResetPasswordPage acceptNewPassword(String newPassword) {
        newPasswordField.sendKeys(newPassword);
        retypeNewPasswordField.sendKeys(newPassword);
        submitButton.click();
        return new SuccessResetPasswordPage(driver);
    }
}
