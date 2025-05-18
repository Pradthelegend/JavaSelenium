package com.practicetestautomation.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver; // Instance Variable
    protected WebDriverWait wait;


    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void visit(String url){
        driver.get(url);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    protected WebElement waitForElement(By Locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
    }

    protected boolean isDisplayed(By Locator){
        try{
            return driver.findElement(Locator).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    protected boolean waitForIsDisplayed(By locator){
        try{
            waitForElement(locator);
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

}
