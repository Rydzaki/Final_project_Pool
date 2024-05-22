package com.pool.pagesSE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

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


    public LoginPage loginValidData(String mail, String password) {
        type(emailInput, mail);
        type(passwordInput, password);
        click(enterBtn);
        return this;

    }

}
