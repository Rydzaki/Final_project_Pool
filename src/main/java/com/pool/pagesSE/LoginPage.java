package com.pool.pagesSE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".modal input[type=text]:nth-child(2)\n")
    WebElement emailInput;
    @FindBy(css = ".modal input[type=password]:nth-child(3)\n")
    WebElement passwordInput;
    @FindBy(css = ".modal button:nth-child(4)")
    WebElement enterBtn;


    public void loginData(String mail, String password) {
        type(emailInput, mail);
        type(passwordInput, password);
        click(enterBtn);

    }

    @FindBy(css = "p.error")
    WebElement errorMessage;

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    @FindBy(css = "a[href='/profile']")
    WebElement profile;
    public boolean isProfileVisible() {
        return profile.isDisplayed();
    }



}
