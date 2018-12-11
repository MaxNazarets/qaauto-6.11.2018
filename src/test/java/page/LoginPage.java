package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for LoginPage.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordButton;

    /**
     * Constructor of LoginPage class.
     * @param driver - webDriver instance from Test.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to login into website on right pages with specific credentials.
     * @param userEmail - String value for userEmail.
     * @param userPass - String value for userPass.
     * @param <T> - generic type of returned PageObjects.
     * @return one of three new PageObjects (HomePage, LoginSubmitPage, LoginPage).
     */
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

    /**
     * Method to check if page is loaded.
     * @return true/false
     */
    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && driver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/");
    }

    /**
     * Method to check "SignIn" button is enabled on page.
     * @return true/false
     */
    public boolean enableSignInButton() {
        return signInButton.isEnabled();
    }

    /**
     * Method that click on 'ForgotPassword' link.
     * @return new RequestResetPasswordPage object.
     */
    public RequestResetPasswordPage clickForgotPasswordButton() {
        forgotPasswordButton.click();
        return new RequestResetPasswordPage(driver);}

}