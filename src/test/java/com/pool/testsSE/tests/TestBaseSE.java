package com.pool.testsSE.tests;


import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;


public class TestBaseSE {

    public static final String VALID_MAIL = "ushakov_test@mail.com";
    public static final String INVALID_MAIL = "@mail.com";
    public static final String VALID_PASSWORD = "Pass12345!";
    public static final String INVALID_PASSWORD = "Pass123455";


    WebDriver driver;

    Logger logger = LoggerFactory.getLogger(TestBaseSE.class);


    @BeforeSuite
    @Parameters("browser")
    public void init(@Optional("chrome") String browser) {
        // Инициализация драйвера в зависимости от переданного браузера
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        //driver.get("http://localhost:5173"); //TODO
        driver.get("https://cohort-34-pool-app-unpfj.ondigitalocean.app"); //TODO
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterSuite(enabled = false)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void startTest(Method method, Object[] p){
        logger.info("Start test: {} with data {}", method.getName(), Arrays.asList(p));
    }
    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED {}", result.getMethod().getMethodName());
        } else {
            logger.error("FAILED {} Screenshot: {}", result.getMethod().getMethodName(), takeScreenShot());
        }
        logger.info("Stop test");
        logger.info("=============================================");
    }

    public String takeScreenShot(){
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File screenshot = new File("screenshots/screen-" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();

    }




}