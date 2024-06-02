package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import com.pool.pagesSE.MyProfilePage;
import com.pool.pagesSE.RegistrationPage;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegistrationTests extends TestBaseSE {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).selectEnterBtn();
    }

    @Test
    public void registrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        int index = 5;
        for (int i = 0; i < index; i++) {
            String email = "testForLogin" + i + "@gm.com";
            registrationPage.fillRegistrationForm("Anna", "Testova", email, VALID_PASSWORD, VALID_PASSWORD, "+79213334567");
            new HomePage(driver).selectEnterBtn();
        }
    }


//    @Test
//    public void registrationWithInvalidEmailFormatTest() {
//        String[] invalidEmails = {"invalidemail", "email@", "email.com", "email@domain", "email@domain."};
//
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//
//        for (String email : invalidEmails) {
//            registrationPage.fillRegistrationForm("Anna", "Testova", email, VALID_PASSWORD, VALID_PASSWORD, "+79213334567");
//
//            //Assert.assertEquals(error, "Неверный логин или пароль");
//
//        }
//
//    }
//
//    @Test
//    public void registrationWithInvalidNameFormatTest() {
//        String[] invalidNames = {"1234", "!@#", "", "name123"};
//
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        for (String name : invalidNames) {
//            registrationPage.fillRegistrationForm(name, "Testova", "email@example.com", VALID_PASSWORD, VALID_PASSWORD, "+79213334567");
//
//            //Assert.assertEquals(error, "Неверный логин или пароль");
//        }
//    }
//
//    @Test
//    public void registrationWithInvalidSurnameFormatTest() {
//        String[] invalidSurnames = {"1234", "!@#", "", "surname123"};
//
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//
//        for (String surname : invalidSurnames) {
//            registrationPage.fillRegistrationForm("Anna", surname, "email@example.com", VALID_PASSWORD, VALID_PASSWORD, "+79213334567");
//
//            // Assert.assertEquals(error, "Неверный логин или пароль");
//
//        }
//    }
//
//    @Test
//    public void registrationWithInvalidPasswordFormatTest() {
//        String[] invalidPasswords = {"", "12345", "password", "!@#$$%^^&*", "a", "12loKo!3456789012345678901", "PASSWORD"};
//
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//
//        for (String password : invalidPasswords) {
//            registrationPage.fillRegistrationForm("Olga", "Ivanova", "email@example.com", password, password, "+79213334567");
//            // Assert.assertEquals(error, "Неверный логин или пароль");
//        }
//    }

    @Test
    public void registrationWithMismatchedPasswordsTest() {
        new RegistrationPage(driver).fillRegistrationForm("Olga", "Ivanova", "email@example.com", VALID_PASSWORD, "Pass12345!!", "+79213334567").alertCheck("Пароли не совпадают");

    }

}

