package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBaseSE {

    @BeforeMethod
    public void precondition(){
        new HomePage(driver).selectEnterBtn();

    }


    @Test
    public void LoginSuccessTest(){
        new LoginPage(driver).loginValidData(VALID_MAIL, VALID_PASSWORD);

    }


}
