package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionsPage extends BasePage {
    private By addButtonLocator = By.xpath("//button[text()='Add']");
    private By editButtonLocator = By.id("edit_btn");
    private By row1InputfieldLocator = By.xpath("//div[@id='row1']/input[@class='input-field']");
    private By row1SaveButtonLocator = By.xpath("(//button[text()='Save'])[1]");
    private By row2InputfieldLocator = By.xpath("//div[@id='row2']/input[@class='input-field']");
    private By row2SaveButtonLocator = By.xpath("(//button[text()='Save'])[2]");
    private By successMessage = By.id("confirmation");
    private By instructorsLocators = By.id("instructions");

    public ExceptionsPage(WebDriver driver){
        super(driver); // Super is a keyword that refers to the parent class(also called the superclass) You basically need to use it to call constructors of parent class.
    }

    public void visit(){
        super.visit("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public void pushAddButton(){
        driver.findElement(addButtonLocator).click();
    }

    public void pushEditButton(){
        driver.findElement(editButtonLocator).click();
    }

    public boolean isRowTwoDisplayedAfterWait(){
        return waitForIsDisplayed(row2InputfieldLocator);
    }

    public void enterFoodInRow1(String name){
        WebElement row1InputField = waitForElement(row1InputfieldLocator);
        row1InputField.clear();
        row1InputField.sendKeys(name);
    }

    public void enterFoodInRow2(String name){
        waitForElement(row2InputfieldLocator).sendKeys(name);
    }

    public void saveRow1(){
        waitForElement(row1SaveButtonLocator).click();
    }

    public void saveRow2(){
        waitForElement(row2SaveButtonLocator).click();
    }

    public String  getSuccessMessage(){
        return waitForElement(successMessage).getText();
    }

    public boolean isInstructionsElementHiddenAfterWait(){
        return waitForIsHidden(instructorsLocators);
    }



}
