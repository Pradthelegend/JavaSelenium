package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private By usernameInputLocator = By.id("username"); // These are Locators not WebElements
    private By passwordInputLocator = By.id("password"); // These are Locators not WebElements
    private By submitButtonLocator = By.id("submit"); // These are Locators not WebElements
    private By errorMessageLocator = By.id("error"); // These are Locators not WebElements

    public LoginPage(WebDriver driver){
         super(driver); // Super is a keyword that refers to the parent class(also called the superclass) You basically need to use it to call constructors of parent class.
    }

    public void visit(){
        super.visit("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public void enterUsername(String username){
        driver.findElement(usernameInputLocator).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    public void submitButton(){
        driver.findElement(submitButtonLocator).click();
    }

    public SuccssfulLoginPage executeLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitButton();
        return new SuccssfulLoginPage(driver);
    }

    public String getErrorMessage(){
        WebElement errorMesageElement = waitForElement(errorMessageLocator);
        return errorMesageElement.getText();
    }


}
