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


    @FindBy(css = "#root > div > main > div > div > ul > li:nth-child(2) > div")
    WebElement usersEmail;
    @FindBy(css = "#root > div > main > div > div > ul > li:nth-child(2) > div > button:nth-child(2)")
    WebElement deleteButton;

    public void deleteUser(String email) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);

        if (usersEmail.getText().contains(email)) {

            wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
            click(deleteButton);
            System.out.println("Удален пользователь: " + email); //TODO
        }

    }


//    public void deleteUser2(String email) {
//        try {
//
//            Robot robot = new Robot();
//            WebDriverWait wait = new WebDriverWait(driver, WAIT);
//
//            // Поиск элемента пользователя по email с помощью xpath
//            WebElement userElement = wait.until(ExpectedConditions.presenceOfElementLocated(
//                    By.xpath("//div[contains(text(), 'martaТ@gm.com')]/parent::li")));
//
//            // Нажмите на элемент пользователя, чтобы открыть подробности
//            userElement.click();
//            robot.delay(200);
//
//            // Поиск кнопки "Удалить" в пределах элемента пользователя
//            WebElement deleteButton = userElement.findElement(By.xpath(".//button[text()='Удалить']"));
//
//            // Нажмите на кнопку "Удалить"
//            deleteButton.click();
//            robot.delay(200);
//
//            // Дождитесь исчезновения элемента пользователя (означает удаление)
//            wait.until(ExpectedConditions.invisibilityOf(userElement));
//
//            System.out.println("Пользователь удален: " + email);
//        } catch (Exception e) {
//            System.out.println("Не удалось удалить пользователя: " + email);
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//
//    public void deleteUser3(String email) {
//        try {
//            // Инициализация Robot
//            Robot robot = new Robot();
//
//            // Поиск элемента с email пользователя (исправлено XPath выражение)
//            WebElement userElement = driver.findElement(By.xpath("//li//text()[contains(., 'martaT@gm.com')]"));
//
//            // Двойной клик на элементе
//            userElement.click();
//            robot.delay(200);
//            userElement.click();
//
//            // Двойное нажатие на табуляцию
//            robot.delay(200);
//            robot.keyPress(KeyEvent.VK_TAB);
//            robot.keyRelease(KeyEvent.VK_TAB);
//            robot.delay(200);
//            robot.keyPress(KeyEvent.VK_TAB);
//            robot.keyRelease(KeyEvent.VK_TAB);
//
//            // Нажатие Enter
//            robot.delay(200);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            // Ожидание исчезновения элемента с email пользователя
//            new WebDriverWait(driver, WAIT).until(ExpectedConditions.invisibilityOf(userElement));
//
//            System.out.println("Удален пользователь: " + email);
//        } catch (Exception e) {
//            System.out.println("Не удалось удалить пользователя: " + email);
//            e.printStackTrace();
//        }
//    }

}




