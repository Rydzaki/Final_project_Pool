package com.pool.testsSE.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBaseSE {

    public static final String VALID_MAIL = "ushakov_test@mail.com";
    public static final String INVALID_MAIL = "@mail.com";
    public static final String VALID_PASSWORD = "Pass12345!";
    public static final String INVALID_PASSWORD = "Pass123455";


    WebDriver driver;

    @BeforeMethod
    public void init(){
        driver = new ChromeDriver();
        driver.get("http://localhost:5173"); //TODO
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = false)
    public void tearDown(){
        driver.quit();
    }

}