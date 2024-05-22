package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends TestBaseSE{

    @BeforeMethod
    public void precondition(){
        new HomePage(driver).selectEnterBtn();
        new LoginPage(driver).loginData(VALID_MAIL, VALID_PASSWORD);
    }

    @Test
    public void logoutPositiveTest(){
        new HomePage(driver).logout();
        Assert.assertTrue(new HomePage(driver).isNoProfile());

    }


}
