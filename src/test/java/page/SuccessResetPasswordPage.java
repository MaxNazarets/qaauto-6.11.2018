package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessResetPasswordPage extends BasePage{

    public SuccessResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement goToHomepageButton;


    public boolean isPageLoaded() {
        return goToHomepageButton.isDisplayed()
                && driver.getTitle().contains("You've successfully reset your password.");
    }

    public HomePage clickGoToHomepageButton() {
        goToHomepageButton.click();
        return new HomePage(driver);
    }
}
