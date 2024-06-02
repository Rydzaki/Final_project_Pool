package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.LoginPage;
import com.pool.pagesSE.MyProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyProfileTests extends TestBaseSE{
    @BeforeMethod
    public void precondition() {

        new HomePage(driver).selectEnterBtn();
        new LoginPage(driver).loginData("ushakov_test@mail.com","Pass12345!");
        new LoginPage(driver).myProfilePage();
    }
    @Test
    public void myProfileTest() {
        MyProfilePage profilePage = new MyProfilePage(driver);
        Assert.assertTrue(profilePage.isDataInProfileVisible());
        Assert.assertEquals(profilePage.getFirstName(), "Имя: Test", "Имя в профиле совпадает с ожидаемым");
        Assert.assertEquals(profilePage.getLastName(), "Фамилия: User", "Фамилия в профиле совпадает с ожидаемой");
    }
}
