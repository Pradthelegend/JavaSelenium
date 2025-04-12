package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        LoginPage loginpage = new LoginPage(driver);
        loginpage.visit();
        // Type username student into Username field
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("student");

        // Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Password123");

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("sbmit"));
        submitButton.click();

        // Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        // Verify new page contains expected text ('Congratulations' or 'successfully logged in')
        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage));

        // Verify button Log out is displayed on the new page
        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Parameters({"username","password","expectedErrorMessage"})
    @Test(groups = {"negative","regression"})
    public void negativeLogintest(String username,String password,String expectedErrorMessage) {

        driver.findElement(By.id("username")).sendKeys(username);
        //Type password Password123 into Password field
        driver.findElement(By.id("password")).sendKeys(password);
        //Push Submit button
        driver.findElement(By.id("submit")).click();
        //Verify error message is displayed
        driver.findElement(By.id("error")).isDisplayed();
        //Verify error message text is Your username is invalid!
        String pageSource = driver.findElement(By.id("error")).getText();
        System.out.println(pageSource);
        Assert.assertTrue(pageSource.contains(expectedErrorMessage));

    }



}


