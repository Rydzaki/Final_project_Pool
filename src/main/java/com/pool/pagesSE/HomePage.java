package com.pool.pagesSE;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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

    public static final Duration WAIT = Duration.ofMillis(3000);

    @FindBy(css = "a")
    List<WebElement> allLinks;

    public void checkBrokenLinks() {
        int numberOfWindowsBefore = driver.getWindowHandles().size(); // Получаем количество открытых окон до проверки
        List<String> linksToClick = new ArrayList<>();

        for (WebElement element : allLinks) {
            String url = element.getAttribute("href");
            boolean verify = verifyLinks(url); // Проверяем статус ссылки
            if ((verify || url.isEmpty()) && url.contains("https")) {
                linksToClick.add(url);
            }
        }

        Actions newTab = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        for (String url : linksToClick) {
            WebElement link = allLinks.stream().filter(e -> e.getAttribute("href").equals(url)).findFirst().orElse(null);
            if (link != null) {
                wait.until(ExpectedConditions.elementToBeClickable(link)); // Ожидаем, пока элемент станет кликабельным
                newTab.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
                int numberOfWindowsAfter = driver.getWindowHandles().size();
                if (numberOfWindowsAfter > numberOfWindowsBefore) {
                    System.out.println(url + "Link opens successfully.");
                } else {
                    System.out.println(url + "Link does not open.");
                }
                closeTab();
            }
        }
    }

}
