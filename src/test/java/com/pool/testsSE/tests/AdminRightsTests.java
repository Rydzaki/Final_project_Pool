package com.pool.testsSE.tests;

import com.pool.pagesSE.AdminPage;
import com.pool.pagesSE.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminRightsTests extends TestBaseSE{
    @BeforeMethod
    public void precondition() {
        new HomePage(driver).selectEnterBtn();
    }

    @Test
    public void testUsersHeaderVisibility() {
        AdminPage adminPage = new AdminPage(driver);
        Assert.assertTrue(adminPage.isUserHeaderVisible());
    }
}
