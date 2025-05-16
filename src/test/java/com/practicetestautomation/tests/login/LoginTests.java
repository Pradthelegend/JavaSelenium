package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccssfulLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void beforeAction(@Optional("chrome") String browser) {
        logger = Logger.getLogger(LoginTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info(browser);
        logger.warning("String");
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            default:
                driver = new ChromeDriver();
                driver.manage().window().maximize();
        }


    }

    @AfterMethod(alwaysRun = true)
    public void afterAction(){
        driver.quit();
    }

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


