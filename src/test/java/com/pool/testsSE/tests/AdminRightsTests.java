package com.pool.testsSE.tests;

import com.pool.pagesSE.AdminPage;
import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminRightsTests extends TestBaseSE{
    @BeforeMethod
    public void precondition() {

        new HomePage(driver).selectEnterBtn();
        new LoginPage(driver).loginData(VALID_MAIL,VALID_PASSWORD);
        new LoginPage(driver).myProfilePage();
    }

    @Test
    public void testUsersHeaderVisibility() {
        AdminPage adminPage = new AdminPage(driver);
        Assert.assertTrue(adminPage.isUserHeaderVisible());
    }
}
