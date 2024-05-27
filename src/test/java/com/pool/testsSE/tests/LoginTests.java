package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBaseSE {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).selectEnterBtn();
    }

    @Test
    public void loginWithValidEmailPositiveTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginData(VALID_MAIL, VALID_PASSWORD);

        boolean isProfileVisible = loginPage.isProfileVisible();
        Assert.assertTrue(isProfileVisible);
    }

    @Test
    public void LoginWithInvalidEmailNegativeTest() {
        new LoginPage(driver).loginData(INVALID_MAIL, VALID_PASSWORD);
        String error = new LoginPage(driver).getErrorMessage();
        Assert.assertEquals(error, "Неверный логин или пароль");
    }

    @Test
    public void LoginWithInvalidPasswordNegativeTest() {
        new LoginPage(driver).loginData(VALID_MAIL, INVALID_PASSWORD);
        String error = new LoginPage(driver).getErrorMessage();
        Assert.assertEquals(error, "Неверный логин или пароль");
    }
}
