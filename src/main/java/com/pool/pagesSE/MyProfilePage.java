package com.pool.pagesSE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends BasePage{
    public MyProfilePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "div > p:nth-of-type(1)")
    WebElement firstname;

    @FindBy(css = "div > p:nth-of-type(2)")
    WebElement lastname;

    public boolean isDataInProfileVisible() {
        return firstname.isDisplayed() && lastname.isDisplayed();
    }
    public String getFirstName() {
        return firstname.getText();
    }

    public String getLastName() {
        return lastname.getText();
    }
}
