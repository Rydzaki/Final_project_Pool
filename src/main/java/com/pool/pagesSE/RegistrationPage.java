package com.pool.pagesSE;


import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy( css = ".modal button:nth-of-type(2)")
    WebElement enter;

    public RegistrationPage selectRegistrationBtn() {
        click(enter);
        return new RegistrationPage(driver);
    }




    @FindBy(css = ".modal input[placeholder='Имя']")
    WebElement firstNameInput;

    @FindBy(css = ".modal input[placeholder='Фамилия']")
    WebElement lastNameInput;

    @FindBy(css = ".modal input[type='email'][placeholder='Email']")
    WebElement emailInput;

    @FindBy(css = ".modal input[type='password'][placeholder='Пароль']")
    WebElement passwordInput;

    @FindBy(css = ".modal input[type='password'][placeholder='Подтвердите пароль']")
    WebElement confirmPasswordInput;

    @FindBy(css = ".modal input[placeholder='Телефон']")
    WebElement phoneInput;

    @FindBy(xpath = "//button[text()='Регистрация']")
    WebElement registrationButton;

    public RegistrationPage fillRegistrationForm(String firstName, String lastName, String email, String password, String confirmPassword, String phone) {
        pause(1000);
        selectRegistrationBtn();
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);
        type(passwordInput, password);
        type(confirmPasswordInput, confirmPassword);
        type(phoneInput, phone);
        click(registrationButton);
        return new RegistrationPage(driver);
    }

    public void  alertCheck(String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        Assert.assertEquals(message, alertText);
    }

}