package com.pool.pagesSE;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.pool.pagesSE.HomePage.WAIT;

public class MyProfilePage extends BasePage {
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


    @FindBy(xpath = "//div[contains(text(), 'ForDelete')]")
    WebElement usersEmail;
    @FindBy(xpath = "//div[contains(text(), 'ForDelete')]/button[text()='Удалить']")
    WebElement deleteButton;

    public void deleteUser(String name) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        click(deleteButton);

    }
    @FindBy(xpath = "//div[contains(text(), 'RegistrationTest')]/button[text()='Удалить']")
    WebElement deleteBtnAfterReristration;

    public void deleteUserAfterRegistration(String name) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtnAfterReristration));
        click(deleteBtnAfterReristration);
        pause(500);

    }
}