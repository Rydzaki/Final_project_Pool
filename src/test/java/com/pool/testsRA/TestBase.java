package com.pool.testsRA;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class TestBase {

    public static final String EMAIL = "ushakov_test@mail.com";
    public static final String PASSWORD = "Pass12345!";
    public static final String MESSAGE = "Login successful";
    public static final String SESSIONID = "JSESSIONID";

    @BeforeMethod
    public void init(){
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api";
    }

    public static Cookies getCookiesForLogin(){
        return given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .extract().response().detailedCookies();

    }
}
