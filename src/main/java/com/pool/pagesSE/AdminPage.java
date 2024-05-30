package com.pool.pagesSE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage{
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div > h2")
    WebElement usersHeader;

    public boolean isUserHeaderVisible() {
        return usersHeader.isDisplayed();

    }
}
