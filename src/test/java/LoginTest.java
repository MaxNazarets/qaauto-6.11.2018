import com.beust.jcommander.Parameter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private LoginSubmitPage loginSubmitPage;

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

    @DataProvider
    public Object[][] validLoginPage() {
        return new Object[][]{
                {"max.nazarets.test@gmail.com", "test12345678"},
                {"max.nazarets.test@GMAIL.COM", "test12345678"},
                {" max.nazarets.test@GMAIL.COM ", "test12345678"},
        };
    }

    @DataProvider
    public Object[][] inValidLoginPage() {
        return new Object[][] {
                {"", "", "Bad"},
                {"", "test12345678"},
                {"max.nazarets.test@gmail.com", ""},
                {" ", " "},
                {"max.nazarets.test@gmail.com", " "},
                {" ", "test12345678"},
        };
    }

    @DataProvider
    public Object[][] inValidLoginSubmitPageEmailRecognize() {
        return new Object[][]{
                {"max.nazarets.wrong@gmail.com", "test12345678", ""},
                {"max.nazarets.wrong@gmail.com", "test123"},
                {"max.nazarets.test@gmailcom", "test12345678"},
        };
    }

    @DataProvider
    public Object[][] inValidLoginSubmitPageUserName() {
        return new Object[][]{
                {"max.nazarets.testgmail.com", "test12345678"},
                {"max.nazarets.test@@gmailcom","test12345678"},
                {"test12345678", "max.nazarets.test@gmail.com"},
                {"<script>alert(123)</script>", "test12345678"},

        };
    }

    @DataProvider
    public Object[][] inValidLoginSubmitPagePass() {
        return new Object[][]{
                {"max.nazarets.test@gmail.com", "test123"},
                {"max.nazarets.test@gmail.com", "t"},
        };
    }


    @Test(dataProvider = "validLoginPage")
    public void positiveLoginTest(String userEmail, String userPass) {
        homePage = loginPage.login(userEmail, userPass);
        homePage.isPageLoaded();
        Assert.assertEquals(homePage.acceptWelcomeMessage(), "Welcome, MaxTest!", "Text welcome is failed");
        homePage.selectMeButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(homePage.acceptProfileName(),"MaxTest NazaretsTest", "Profile name of user is different");
    }

    @Test(dataProvider = "inValidLoginPage")
    public void negativeLoginTest(String userEmail, String userPass) {
        loginPage.login(userEmail, userPass);
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
        Assert.assertTrue(loginPage.isPageLoaded(),"LogIn page is not loaded");
    }

    @Test(dataProvider = "inValidLoginSubmitPageEmailRecognize")
    public void wrongUserEmailTest(String userEmail,
                                   String userPass,
                                   String errorEmail,
                                   String errorPass,
                                   String borderColorEmail,
                                   String borderColorPass) {
        loginPage.login(userEmail, userPass);
        loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorEmailField(), "Hmm, we don't recognize that email. Please try again.","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getBorderColorEmailField(),"#d11124","The color of emailField's border is not red");
        Assert.assertEquals(loginSubmitPage.getErrorPassField(),"","userPass Validation in empty");
    }

    @Test(dataProvider = "inValidLoginSubmitPageUserName")
    public void wrongUserEmailTextTest(String userEmail, String userPass) {
        loginPage.login(userEmail, userPass);
        loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorEmailField(), "Please enter a valid username","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getBorderColorEmailField(),"#d11124","The color of emailField's border is not red");
        Assert.assertEquals(loginSubmitPage.getErrorPassField(),"","userPass Validation in empty");
    }

    @Test(dataProvider = "inValidLoginSubmitPagePass")
    public void wrongUserPassTest(String userEmail, String userPass) {

        loginPage.login(userEmail, userPass);
        loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorPassField(), "Hmm, that's not the right password. Please try again or request a new one.","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getBorderColorPassField(),"#d11124","The color of passField's border is not red");
    }

}
