package com.pool.pagesSE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(css = ".modal button:nth-child(1)")
    WebElement registrationButton;

    public RegistrationPage fillRegistrationForm(String firstName, String lastName, String email, String password, String confirmPassword, String phone) {
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


}
