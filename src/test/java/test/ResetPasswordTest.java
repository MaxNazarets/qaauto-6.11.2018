package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void resetPasswordTest() {
    String userEmail = "max.nazarets.tst@gmail.com";
    String newPassword = "makrusnet123";

    Assert.assertTrue(loginPage.isPageLoaded(),"Page is not loaded");

    RequestResetPasswordPage requestResetPasswordPage = loginPage.clickForgotPasswordButton();
    Assert.assertTrue(requestResetPasswordPage.isPageLoaded(),"Page is not loaded");

    requestResetPasswordPage.findAccountSubmit(userEmail);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    SetNewPasswordPage setNewPasswordPage = new SetNewPasswordPage(driver);
    Assert.assertTrue(setNewPasswordPage.isPageLoaded(),"Page is not loaded");

    SuccessResetPasswordPage successResetPasswordPage = setNewPasswordPage.acceptNewPassword(newPassword);
    Assert.assertTrue(successResetPasswordPage.isPageLoaded(),"Page is not loaded");
//
//    HomePage homePage = successResetPasswordPage.clickGoToHomepageButton();
//    Assert.assertTrue(homePage.isPageLoaded(),"Page is not loaded");

    }

}
