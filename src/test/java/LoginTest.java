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
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"max.nazarets.test@gmail.com", "test12345678"},
                {"max.nazarets.test@GMAIL.COM", "test12345678"},
                {" max.nazarets.test@GMAIL.COM ", "test12345678"},
        };
    }
    @Test(dataProvider = "validDataProvider")
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

    @Test
    public void wrongUserEmailTest() {

        loginPage.login("max.nazarets.wrong@gmail.com", "test12345678");
        loginSubmitPage = new LoginSubmitPage(driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorEmailField(), "Hmm, we don't recognize that email. Please try again.","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getTextEmailField(),"max.nazarets.wrong@gmail.com", "email value is not be showed");
//        Assert.assertEquals(loginSubmitPage.getBorderColorEmailField(),"#d11124","The color of emailField's border is not red");
    }

    @Test
    public void wrongUserPassTest() {

        loginPage.login("max.nazarets.test@gmail.com", "test123");
        loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorPassField(), "Hmm, that's not the right password. Please try again or request a new one.","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getTextEmailField(),"max.nazarets.test@gmail.com", "email value is not be showed");
        Assert.assertEquals(loginSubmitPage.getBorderColorPassField(),"#d11124","The color of passField's border is not red");
    }

    @Test
    public void wrongUserPassMinCharTest() {
        loginPage.login("max.nazarets.test@gmail.com", "test");
        loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorPassField(), "Hmm, that's not the right password. Please try again or request a new one.","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getTextEmailField(),"max.nazarets.test@gmail.com", "email is not be showed");
        Assert.assertEquals(loginSubmitPage.getBorderColorPassField(),"#d11124","The color of passField's border is not red");

    }

    @Test
    public void wrongUserEmailDogTest() {
        loginPage.login("max.nazarets.testgmail.com", "test12345678");
        loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorEmailField(), "Please enter a valid username","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getTextEmailField(),"max.nazarets.testgmail.com", "email is not be showed");
        Assert.assertEquals(loginSubmitPage.getBorderColorEmailField(),"#d11124","The color of emailField's border is not red");
    }

    @Test
    public void wrongUserEmailDotTest() {
        loginPage.login("max.nazarets.test@gmailcom", "test12345678");
        loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertEquals(driver.getTitle(),"Sign In to LinkedIn", "Login Page title is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorEmailField(), "Hmm, we don't recognize that email. Please try again.","Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getTextEmailField(),"max.nazarets.testgmail.com", "email is not be showed");
        Assert.assertEquals(loginSubmitPage.getBorderColorEmailField(),"#d11124","The color of emailField's border is not red");
    }

    @Test
    public void wrongUserEmailSpaceTest() {
        loginPage.login(" ", "test12345678");
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
    }

    @Test
    public void wrongUserPassSpaceTest() {
        loginPage.login("max.nazarets.test@gmail.com", " ");
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
    }

    @Test
    public void wrongUserNamePassSpacesTest() {
        loginPage.login(" ", " ");
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
    }

    @Test
    public void emptyUserNameTest() {
        loginPage.login("", "test12345678");
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
    }

    @Test
    public void emptyUserPassTest() {
        loginPage.login("max.nazarets.test@gmail.com", "");
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
    }

    @Test
    public void emptyUserNamePassTest() {
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
    }

    @Test
    public void NegativeErrorsTest() {
        LoginSubmitPage loginSubmitPage = loginPage.login("max.nazarets.test@@gmail.com","wrong");
        Assert.assertTrue(loginSubmitPage.isPageLoaded(),"Login Submit page is not loaded");
        Assert.assertEquals(loginSubmitPage.getErrorEmailField(),"Hmm, we don't recognize that email. Please try again.","userEmail validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getErrorPassField(),"","userPass Validation in empty");
    }

    @Test
    public void negativeLoginTest() {
        loginPage.login("a@b.c","");
        Assert.assertTrue(loginPage.isPageLoaded(),"LogIn page is not loaded");
    }

}
