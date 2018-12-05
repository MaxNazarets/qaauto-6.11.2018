package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public <T> T login(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        if (driver.getCurrentUrl().contains("/feed")) {
            return (T) new HomePage(driver);
        }
        if (driver.getCurrentUrl().contains("/login-submit")) {
            return (T) new LoginSubmitPage(driver);
        }
        else {
            return (T) new LoginPage(driver);
        }
    }

    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && driver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/");
    }

    public boolean enableSignInButton() {
        return signInButton.isEnabled();
    }

}
