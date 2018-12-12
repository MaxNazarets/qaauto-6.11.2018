package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for LoginSubmitPage.
 */
public class LoginSubmitPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passField;

    @FindBy(xpath = "//form[@class='login__form']")
    private WebElement loginForm;

    /**
     * Constructor of LoginSubmitPage class.
     *
     * @param driver - webDriver instance from Test.
     */
    public LoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to check if page is loaded.
     *
     * @return true/false
     */
    public boolean isPageLoaded() {
        return loginForm.isDisplayed()
                && driver.getTitle().equals("Sign In to LinkedIn")
                && driver.getCurrentUrl().contains("uas/login-submit");
    }

    /**
     * Method to getText from errorEmailField.
     *
     * @return String value of field
     */
    public String getErrorEmailField() {
        return driver.findElement(By.xpath("//div[@id='error-for-username']")).getText();
    }

    /**
     * Method to getText from errorPassField.
     *
     * @return String value of field
     */
    public String getErrorPassField() {
        return driver.findElement(By.xpath("//div[@id='error-for-password']")).getText();
    }

}
