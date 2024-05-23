package com.pool.pagesSE;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

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

    @FindBy(css = "a")
    List<WebElement> allLinks;

    @FindBy(css = "a[href='https://ok.ru/']")
    WebElement urlToOpen;

    public void checkBrokenLinks() {
        int numberOfWindowsBefore = driver.getWindowHandles().size(); // Получаем количество открытых окон до проверки

        for (WebElement element : allLinks) {
            String url = element.getAttribute("href");
            boolean verify =  verifyLinks(url); // Проверяем статус ссылки
            if ((verify|| url.isEmpty()) && url.contains("https")) {
                // Если ссылка null или пустая, открываем окно
                click(element);
                pause(1000);
                // Проверяем количество открытых окон после клика
                int numberOfWindowsAfter = driver.getWindowHandles().size();
//                System.out.println(numberOfWindowsAfter - numberOfWindowsBefore + " - ссылок всего на странице"); //todo
                if (numberOfWindowsAfter > numberOfWindowsBefore) {
                    System.out.println("Link opens successfully.");
                } else {
                    System.out.println("Link does not open.");
                }
               // driver.close();
            }

        }
    }

}
