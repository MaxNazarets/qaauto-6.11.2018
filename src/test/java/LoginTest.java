import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("max.nazarets.test@gmail.com", "");
            Assert.assertEquals(driver.getTitle(),"LinkedIn: Войти или зарегистрироваться", "Login Page title is wrong");
            Assert.assertTrue(loginPage.signInButton.isDisplayed(), "SignInButton is not displayed");
    }

    @Test
    public void positiveLoginTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("max.nazarets.test@gmail.com","test12345678");
        WebElement acceptWelcomeText = driver.findElement(By.xpath("//a[@data-control-name='identity_welcome_message']"));
            Assert.assertEquals(acceptWelcomeText.getText(), "Добро пожаловать, MaxTest!", "Text welcome is failed");
        WebElement button = driver.findElement(By.xpath("//button[@data-control-name='nav.settings']"));
        button.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement acceptProfileName = driver.findElement(By.xpath("//div[@class='nav-settings__member-info-container']/h3"));
            Assert.assertEquals(acceptProfileName.getText(),"MaxTest NazaretsTest", "Profile name of user is wrong");
    }

}
