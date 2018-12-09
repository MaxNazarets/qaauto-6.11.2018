package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ResetPasswordTest extends BaseTest {

    /**
     * Precondition
     * - Open Browser
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Login Page is loaded
     * - Click on "ForgotPassword" button
     * - Verify RequestResetPassword Page is loaded
     * - Enter in email field current gmail's email address
     * - Click on "Find account" button
     * - Open PostBox
     * - Open Email's reset mail
     * - Get URL from the mail
     * - Put this URL in browser
     * - Verify SetNewPassword Page is loaded
     * - Enter in "New Password" field different pass
     * - Enter in "Retype Password" field the same pass
     * - Click on "Submit" button
     * - Verify SuccessResetPassword Page is loaded
     * - Click on "Go To Homepage" button
     * - Verify Home Page is loaded
     *
     * PostCondition:
     * - Close browser
     */

    @Test
    public void resetPasswordTest() {
    String userEmail = "max.nazarets.tst@gmail.com";
    String newPassword = "makrusnet123";

    Assert.assertTrue(loginPage.isPageLoaded(),"LoginPage is not loaded");

    RequestResetPasswordPage requestResetPasswordPage = loginPage.clickForgotPasswordButton();
    Assert.assertTrue(requestResetPasswordPage.isPageLoaded(),"RequestResetPasswordPage is not loaded");

    requestResetPasswordPage.findAccount(userEmail);

    SubmitResetPasswordPage submitResetPasswordPage = new SubmitResetPasswordPage(driver);
    Assert.assertTrue(submitResetPasswordPage.isPageLoaded(),"SubmitResetPasswordPage is not loaded");

    SetNewPasswordPage setNewPasswordPage = submitResetPasswordPage.navigateToLinkFromEmail();

    Assert.assertTrue(setNewPasswordPage.isPageLoaded(),"SetNewPasswordPage is not loaded");

    SuccessResetPasswordPage successResetPasswordPage = setNewPasswordPage.acceptNewPassword(newPassword);
    Assert.assertTrue(successResetPasswordPage.isPageLoaded(),"SuccessResetPasswordPage is not loaded");

    HomePage homePage = successResetPasswordPage.clickGoToHomepageButton();
    Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not loaded");
    }

}
