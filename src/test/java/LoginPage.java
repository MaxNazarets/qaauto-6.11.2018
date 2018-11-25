import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

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

    public LoginSubmitPage loginToLoginSubmit(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        return new LoginSubmitPage(driver);
    }

    public HomePage loginToHome(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        return new HomePage(driver);
    }

    public void login(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
    }

    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && driver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && driver.getCurrentUrl().equals("https://www.linkedin.com/");
    }

    public Boolean enableSignInButton() {
        return signInButton.isEnabled();
    }

}
