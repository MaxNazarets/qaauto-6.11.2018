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
    LoginPage loginPage;
    LoginCheckpointPage loginCheckpointPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void positiveLoginTest() {

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
            Assert.assertEquals(acceptProfileName.getText(),"MaxTest NazaretsTest", "Profile name of user is different");
    }

    @Test
    public void wrongUserEmailTest() {

        loginPage.login("max.nazarets.wrong@gmail.com", "test12345678");
        loginCheckpointPage = new LoginCheckpointPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginCheckpointPage.errorEmailField.getText(), "Hmm, we don't recognize that email. Please try again.","Error is not be showed");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("value"),"max.nazarets.wrong@gmail.com", "email is not the same");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("color"),"#d11124","color of border is not red");
    }

    @Test
    public void wrongUserPassTest() {

        loginPage.login("max.nazarets.test@gmail.com", "test123");
        loginCheckpointPage = new LoginCheckpointPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginCheckpointPage.errorPassField.getText(), "Hmm, that's not the right password. Please try again or ","Error is not be showed");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("value"),"max.nazarets.wrong@gmail.com", "email is not be showed");
        Assert.assertEquals(loginCheckpointPage.passField.getAttribute("color"),"#d11124","color of border is not red");
    }

    @Test
    public void wrongUserPassMinCharTest() {
        loginPage.login("max.nazarets.test@gmail.com", "test");
        loginCheckpointPage = new LoginCheckpointPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginCheckpointPage.errorPassField.getText(), "The password you provided must have at least 6 characters.","Error is not be showed");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("value"),"max.nazarets.wrong@gmail.com", "email is not be showed");
        Assert.assertEquals(loginCheckpointPage.passField.getAttribute("color"),"#d11124","color of border is not red");

    }

    @Test
    public void wrongUserEmailDogTest() {
        loginPage.login("max.nazarets.testgmail.com", "test12345678");
        loginCheckpointPage = new LoginCheckpointPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginCheckpointPage.errorEmailField.getText(), "Please enter a valid username","Error is not be showed");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("value"),"max.nazarets.testgmail.com", "email is not be showed");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("color"),"#d11124","color of border is not red");
    }

    @Test
    public void wrongUserEmailDotTest() {
        loginPage.login("max.nazarets.test@gmailcom", "test12345678");
        loginCheckpointPage = new LoginCheckpointPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginCheckpointPage.errorEmailField.getText(), "Hmm, we don't recognize that email. Please try again.","Error is not be showed");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("value"),"max.nazarets.testgmail.com", "email is not be showed");
        Assert.assertEquals(loginCheckpointPage.emailField.getAttribute("color"),"#d11124","color of border is not red");
    }

    @Test
    public void wrongUserEmailSpaceTest() {
        loginPage.login(" ", "test12345678");
        Assert.assertEquals(loginPage.signInButton.isEnabled(), false,"signIn button is active");


    }

    @Test
    public void wrongUserPassSpaceTest() {
        loginPage.login("max.nazarets.test@gmail.com", " ");
        Assert.assertEquals(loginPage.signInButton.isEnabled(), false,"signIn button is active");
    }

    @Test
    public void wrongUserNamePassSpacesTest() {
        loginPage.login(" ", " ");
        Assert.assertEquals(loginPage.signInButton.isEnabled(), false,"signIn button is active");
    }

    @Test
    public void emptyUserNameTest() {
        loginPage.login("", "test12345678");
        Assert.assertEquals(loginPage.signInButton.isEnabled(), false,"signIn button is active");

    }

    @Test
    public void emptyUserPassTest() {
        loginPage.login("max.nazarets.test@gmail.com", "");
        Assert.assertEquals(loginPage.signInButton.isEnabled(), false,"signIn button is active");
    }

    @Test
    public void emptyUserNamePassTest() {
        Assert.assertEquals(loginPage.signInButton.isEnabled(), false,"signIn button is active");
    }



}
