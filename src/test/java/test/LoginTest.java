package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest{

    @DataProvider
    public Object[][] validLoginData() {
        return new Object[][]{
                {"max.nazarets.tst@gmail.com", "makrusnet123"},
                {"max.nazarets.tst@GMAIL.COM", "makrusnet123"},
                {" max.nazarets.tst@GMAIL.COM ", "makrusnet123"},
        };
    }

    @Test(dataProvider = "validLoginData")
    public void positiveLoginTest(String userEmail, String userPass) {
        HomePage homePage = loginPage.login(userEmail, userPass);
        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not loaded.");
        homePage.selectMeButton();
        Assert.assertEquals(homePage.acceptProfileName(),"MaxTest NazaretsTest", "Profile name of user is wrong");
    }

    @DataProvider
    public Object[][] inValidLoginData() {
        return new Object[][] {
                {"", ""},
                {"", "makrusnet123"},
                {"max.nazarets.tst@gmail.com", ""},
                {" ", " "},
                {"max.nazarets.tst@gmail.com", " "},
                {" ", "makrusnet123"},
        };
    }

    @Test(dataProvider = "inValidLoginData")
    public void negativeLoginTest(String userEmail, String userPass) {
        loginPage.login(userEmail, userPass);
        Assert.assertFalse(loginPage.enableSignInButton(), "signIn button is active");
        Assert.assertTrue(loginPage.isPageLoaded(),"LoginPage is not loaded");
    }

    @DataProvider
    public Object[][] inValidLoginSubmitData() {
        return new Object[][]{
                {"max.nazarets.wrong@gmail.com", "makrusnet123",
                        "Hmm, we don't recognize that email. Please try again.", ""},
                {"max.nazarets.wrong@gmail.com", "test123",
                        "Hmm, we don't recognize that email. Please try again.", ""},
                {"max.nazarets.tst@gmailcom", "makrusnet123",
                        "Hmm, we don't recognize that email. Please try again.", ""},

                {"max.nazarets.tsstgmail.com", "makrusnet123",
                        "Please enter a valid email address.", ""},
                {"max.nazarets.tst@@gmailcom","makrusnet123",
                        "We don't recognize that email." +
                                "Did you mean: @gmail.com?", ""},
                {"makrusnet123", "max.nazarets.tst@gmail.com",
                        "Please enter a valid email address.", ""},
                {"<script>alert(123)</script>", "makrusnet123",
                        "Please enter a valid email address.", ""},

                {"max.nazarets.tst@gmail.com", "test123",
                        "", "Hmm, that's not the right password. Please try again or request a new one."},
                {"max.nazarets.tst@gmail.com", "t",
                        "", "Hmm, that's not the right password. Please try again or request a new one."},
        };
    }

    @Test(dataProvider = "inValidLoginSubmitData")
    public void negativeLoginSubmitTest(String userEmail,
                                        String userPass,
                                        String errorEmailMessage,
                                        String errorPassMessage) {
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPass);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(),"LoginSubmitPage is not loaded.");
        Assert.assertEquals(loginSubmitPage.getErrorEmailField(), errorEmailMessage,"Error is not be showed");
        Assert.assertEquals(loginSubmitPage.getErrorPassField(), errorPassMessage, "errorPass is wrong");
    }

}
