package com.pool.pagesSE;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public abstract class BasePage {

    WebDriver driver;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void clickJS(WebElement element, int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        click(element);
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitUntilUrlToBe(String url) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until((ExpectedCondition<Boolean>) d -> d.getCurrentUrl().equals(url));
    }

    public boolean verifyLinks(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() >= 400) {
                System.out.println(linkUrl + " - " + httpURLConnection.getResponseMessage() + " is a broken link");
            } else {
                System.out.println(linkUrl + " - " + httpURLConnection.getResponseMessage());
            }
        } catch (Exception e) {
            System.out.println(linkUrl + " - " + e.getMessage() + " ERROR occurred");
        }
        return true;

    }

    public void closeTab() {
        String mainWindowHandle = driver.getWindowHandle();

        Set<String> allWindowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(allWindowHandles);
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(tabs.size() - 1));
            driver.close();
            driver.switchTo().window(mainWindowHandle);
        } else {
            System.out.println("Нет новой вкладки для закрытия.");
        }
    }




}
