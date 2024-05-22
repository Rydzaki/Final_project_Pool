package com.pool.pagesSE;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".nav-button")
    WebElement enter;

    public LoginPage selectEnterBtn() {
        click(enter);
        return new LoginPage(driver);

    }

    @FindBy(css = "button.nav-button")
    WebElement exitBtn;
    public void logout() {
        pause(500);
        click(exitBtn);
    }

    @FindBy(css = "a[href='/profile']")
    WebElement profile;

    public boolean isNoProfile() {
        try {
            return !profile.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }
}
