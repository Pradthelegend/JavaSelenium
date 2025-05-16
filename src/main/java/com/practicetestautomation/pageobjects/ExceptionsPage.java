package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExceptionsPage extends BasePage {

    public ExceptionsPage(WebDriver driver){
        super(driver); // Super is a keyword that refers to the parent class(also called the superclass) You basically need to use it to call constructors of parent class.
    }

    public void visit(){
        super.visit("https://practicetestautomation.com/practice-test-login/");
    }

    private By addButtonLocator = By.xpath("//button[text()='Add']");
    private By row2InputfieldLocator = By.xpath("//div[@id='row2']/input[@class='input-field']");

}
