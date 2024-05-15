package com.pool.testsRA;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    
    public static final String EMAIL = "ushakov_test@mail.com";
    public static final String PASSWORD = "Pass12345!";
    public static final String MESSAGE = "Login successful";
    
    @BeforeMethod
    public void init(){
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api";
    }
    

}
