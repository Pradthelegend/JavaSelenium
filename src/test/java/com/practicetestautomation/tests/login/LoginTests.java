package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccssfulLoginPage;
import com.practicetestautomation.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"positive","regression","smoke"})
    public void testLoginFunctionality() {
        logger.info("Strrting testLoginFunctionality");
        LoginPage loginpage = new LoginPage(driver);
        loginpage.visit();
        SuccssfulLoginPage succssfulLoginPage = loginpage.executeLogin("student","Password123");
        succssfulLoginPage.load();
        succssfulLoginPage.isLogoutButtonDisplayed();
        logger.info("Verify the login functionality");
        Assert.assertEquals(succssfulLoginPage.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");
        Assert.assertTrue(succssfulLoginPage.getPageSource().contains("Congratulations student. You successfully logged in!"));
        Assert.assertTrue(succssfulLoginPage.isLogoutButtonDisplayed());
    }

    @Parameters({"username","password","expectedErrorMessage"})
    @Test(groups = {"negative","regression"})
    public void negativeLogintest(String username,String password,String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLogin(username,password);
        Assert.assertEquals(loginPage.getErrorMessage(),expectedErrorMessage);
    }



}


