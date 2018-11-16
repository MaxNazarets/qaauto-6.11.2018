import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    private static WebDriver driver;

//    @BeforeMethod
//    public void setup() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://www.linkedin.com");
//    }

    @Test
    public void negativeLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        WebElement emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        emailField.sendKeys("a@b.c");
        passwordField.sendKeys("");
        signInButton.click();
            Assert.assertEquals(driver.getTitle(),"LinkedIn: Войти или зарегистрироваться");
        driver.quit();
    }

    @Test
    public void positiveLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        WebElement emailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        emailField.sendKeys("max.nazarets.test@gmail.com");
        passwordField.sendKeys("test12345678");
        signInButton.click();
        WebElement acceptWelcomeText = driver.findElement(By.xpath("//a[@data-control-name='identity_welcome_message']"));
            Assert.assertEquals(acceptWelcomeText.getText(), "Добро пожаловать, MaxTest!");
        WebElement button = driver.findElement(By.xpath("//button[@data-control-name='nav.settings']"));
        button.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement acceptProfileName = driver.findElement(By.xpath("//div[@class='nav-settings__member-info-container']/h3"));
            Assert.assertEquals(acceptProfileName.getText(),"MaxTest NazaretsTest");
        driver.quit();
    }

//    @AfterMethod
//    public void quit() {
//        driver.quit();
//    }
}
