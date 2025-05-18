package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExceptionsPage extends BasePage {
    private By addButtonLocator = By.xpath("//button[text()='Add']");
    private By row2InputfieldLocator = By.xpath("//div[@id='row2']/input[@class='input-field']");
    private By row2SaveButtonLocator = By.xpath("(//button[text()='Save'])[2]");
    private By successMessage = By.id("confirmation");

    public ExceptionsPage(WebDriver driver){
        super(driver); // Super is a keyword that refers to the parent class(also called the superclass) You basically need to use it to call constructors of parent class.
    }

    public void visit(){
        super.visit("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public void pushAddButton(){
        driver.findElement(addButtonLocator).click();
    }

    public boolean isRowTwoDisplayedAfterWait(){
        return waitForIsDisplayed(row2InputfieldLocator);
    }

    public void enterFoodInRow2(String name){
        waitForElement(row2InputfieldLocator).sendKeys(name);
    }

    public void saveRow2(){
        waitForElement(row2SaveButtonLocator).click();
    }

    public String  getSuccessMessage(){
        return waitForElement(successMessage).getText();
    }



}
