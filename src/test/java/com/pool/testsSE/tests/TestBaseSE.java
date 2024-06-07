package com.pool.testsSE.tests;

import com.google.common.io.Files;
import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.RegistrationPage;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestBaseSE {

    protected static String login;
    protected static String password;

    static {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("/config.properties")) {
            props.load(input);
            login = props.getProperty("LOGIN");
            password = props.getProperty("PASSWORD");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static final String CONFIG_FILE = "config.properties";
    private static final Map<String, String> CONFIG_VARS = new HashMap<>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(CONFIG_FILE));
            CONFIG_VARS.put("LOGIN", properties.getProperty("LOGIN"));
            CONFIG_VARS.put("PASSWORD", properties.getProperty("PASSWORD"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static final String VALID_MAIL = CONFIG_VARS.getOrDefault("LOGIN", null);
    public static final String INVALID_MAIL = "@mail.com";
    public static final String VALID_PASSWORD = CONFIG_VARS.getOrDefault("PASSWORD", null);
    public static final String INVALID_PASSWORD = "Pass123455";

    WebDriver driver;

    Logger logger = LoggerFactory.getLogger(TestBaseSE.class);

    @BeforeMethod
    @Parameters("browser")
    public void init(@Optional("chrome") String browser) {
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
        driver.get("https://cohort-34-pool-app-unpfj.ondigitalocean.app/#/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void startTest(Method method, Object[] p) {
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

    public String takeScreenShot() {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File screenshot = new File("screenshots/screen-" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();
    }

    public void registrationNewUser(String email) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationForm("ForDelete", "Testova", email, VALID_PASSWORD, VALID_PASSWORD, "+79213334567");
        new HomePage(driver).selectEnterBtn();
    }
}
