package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.LoginPage;
import com.pool.pagesSE.MyProfilePage;
import com.pool.pagesSE.RegistrationPage;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegistrationTests extends TestBaseSE {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).selectEnterBtn();
    }

    @Test
    public void registrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String email = "testForLogin@gm.com";
        registrationPage.fillRegistrationForm("RegistrationTest", "Testova", email, VALID_PASSWORD, VALID_PASSWORD, "+79213334567");
        new HomePage(driver).selectEnterBtn();

    }

    @AfterMethod
    public void postcondition() {
        new HomePage(driver).selectEnterBtn();
        new LoginPage(driver).loginInvalidData(VALID_MAIL, VALID_PASSWORD);
        new LoginPage(driver).myProfilePage();
        new MyProfilePage(driver).deleteUserAfterRegistration("RegistrationTest");
    }
}



