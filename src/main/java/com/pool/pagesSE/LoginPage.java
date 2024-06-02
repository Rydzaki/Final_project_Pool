package com.pool.pagesSE;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#root > div > header > div.modal > input[type=text]:nth-child(2)")
    WebElement emailInput;
    @FindBy(css = ".modal input[type=password]:nth-child(3)")
    WebElement passwordInput;
    @FindBy(css = ".modal button:nth-child(4)")
    WebElement enterBtn;

    @FindBy (css = "#root > div > header > div.modal > input[type=text]:nth-child(3)")
    WebElement wrongEmail;
    @FindBy (css = "#root > div > header > div.modal > input[type=password]:nth-child(4)")
    WebElement wrongPassword;
    @FindBy (css = "#root > div > header > div.modal > button:nth-child(5)")
    WebElement enterBtnIfWrong;

    public void loginData(String mail, String password) {
        pause(500);
        type(emailInput, mail);
        pause(500);
        type(passwordInput, password);
        click(enterBtn);
    }
    public void loginInvalidData(String mail, String password) {
        pause(500);
        type(wrongEmail, mail);
        pause(500);
        type(wrongPassword, password);
        click(enterBtnIfWrong);
    }


    @FindBy(css = "p.error")
    WebElement errorMessage;

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    @FindBy(css = "a[href='#/profile']")
    WebElement profile;

    public boolean isProfileVisible() {
        return profile.isDisplayed();

    }

    public void myProfilePage(){
        click(profile);
    }

    @FindBy(css = "div:nth-child(2) div.app header.header div.modal > button:nth-child(7)")
    WebElement closeBtn;

    public void clickCloseBtn() {
        click(closeBtn);
    }

    @FindBy(css = "button.nav-button")
    WebElement quitBtn;

    public void logout() {
        click(quitBtn);
    }
}
