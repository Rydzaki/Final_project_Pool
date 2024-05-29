package com.pool.pagesSE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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



    @FindBy(css = "#root > div > main > div > div > ul > li:nth-child(10) > div")
    WebElement usersEmail;
    @FindBy (css = "#root > div > main > div > div > ul > li:nth-child(10) > div > button:nth-child(2)")
    WebElement deleteButton;

    public void deleteUser(String email) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        pause(1000);

            if (usersEmail.getText().contains(email)) {

                wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
                click(deleteButton);
                System.out.println("Удален пользователь: " + email); //TODO
            }

    }
}
