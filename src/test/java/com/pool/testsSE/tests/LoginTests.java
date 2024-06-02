package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class LoginTests extends TestBaseSE {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void precondition() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        homePage.selectEnterBtn();

    }

    @Test(priority = 1)
    public void loginWithValidEmailPositiveTest() {
        loginPage.loginData(VALID_MAIL, VALID_PASSWORD);
        boolean isProfileVisible = loginPage.isProfileVisible();
        Assert.assertTrue(isProfileVisible, "Profile should be visible after successful login");
        loginPage.logout();
    }

    @Test(priority = 2)
    public void loginWithInvalidEmailNegativeTest() {
        loginPage.loginData(INVALID_MAIL, VALID_PASSWORD);
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Неверный логин или пароль", "Error message should match for invalid password");
        //loginPage.clickCloseBtn();
    }

    @Test(priority = 3)
    public void loginWithInvalidPasswordNegativeTest() {
        loginPage.loginData(VALID_MAIL, INVALID_PASSWORD);
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Неверный логин или пароль", "Error message should match for invalid password");
        //loginPage.clickCloseBtn();
    }
}
