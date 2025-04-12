package com.practicetestautomation.tests.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='row2']/input[@class='input-field']")).isDisplayed(),"Row2 error message is not displayed");

    }

    @Test
    public void timeOutexception(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']")));

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='row2']/input[@class='input-field']")).isDisplayed(),"Row2 error message is not displayed");
    }

    @Test
    public void elementNotInteractableException(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']"))).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='row2']/input[@class='input-field']")).isDisplayed(),"Row2 error message is not displayed");
        action.sendKeys("Test").perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[text()='Save'])[2]"))).click();
        String  actualMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmation"))).getText();
        String expectedMessage = "Row 2 was saved";
        Assert.assertEquals(actualMessage,expectedMessage);
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


