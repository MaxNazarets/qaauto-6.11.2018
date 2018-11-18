import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginCheckpointPage {

    WebDriver driver;

    WebElement errorEmailField;
    WebElement errorPassField;
    WebElement emailField;
    WebElement labelEmailField;
    WebElement passField;

    public LoginCheckpointPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        emailField = driver.findElement(By.xpath("//input[@id='username']"));
        errorEmailField = driver.findElement(By.xpath("//div[@id='error-for-username']"));
        labelEmailField = driver.findElement(By.xpath("//label[@for='username']"));

        passField = driver.findElement(By.xpath("//input[@id='password']"));
        errorPassField = driver.findElement(By.xpath("//div[@id='error-for-password']"));
    }
}
