package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passField;

    @FindBy(xpath = "//form[@class='login__form']")
    private WebElement loginForm;

    public LoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return loginForm.isDisplayed()
                && driver.getTitle().equals("Sign In to LinkedIn")
                && driver.getCurrentUrl().contains("uas/login-submit");
    }

    public String getErrorEmailField() {
        return driver.findElement(By.xpath("//div[@id='error-for-username']")).getText();
    }

    public String getErrorPassField() {
        return driver.findElement(By.xpath("//div[@id='error-for-password']")).getText();
    }

}
