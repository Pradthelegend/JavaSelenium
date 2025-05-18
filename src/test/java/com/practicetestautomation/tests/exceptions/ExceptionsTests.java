package com.practicetestautomation.tests.exceptions;

import com.practicetestautomation.pageobjects.ExceptionsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void beforeAction(@Optional("chrome") String browser) {
        logger = Logger.getLogger(ExceptionsTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info(browser);
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
        //Implicit Wait
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }
    // Open page
//        driver = new ChromeDriver();
//        driver.get("https://practicetestautomation.com/practice-test-login/");

    @AfterMethod(alwaysRun = true)
    public void afterAction(){
        driver.quit();
    }
    @Test
    public void noSuchElementException() {

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushAddButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row2 error message is not displayed");

    }

    @Test
    public void timeOutexception(){
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushAddButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row2 error message is not displayed");

    }

    @Test
    public void elementNotInteractableException(){

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.visit();
        exceptionsPage.pushAddButton();
        exceptionsPage.isRowTwoDisplayedAfterWait();
        exceptionsPage.enterFoodInRow2("Sushi");
        exceptionsPage.saveRow2();
        Assert.assertEquals(exceptionsPage.getSuccessMessage(),"Row 2 was saved","Message is not fisible");

    }

    @Test
    public void invalidElementStateException(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Pizza']"))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Pizza']"))).sendKeys("Hello");
    }

    @Test
    public void staleElementReferenceException(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='add_btn']"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))),"The Element is still dispalyed--ERROR");
    }





}


