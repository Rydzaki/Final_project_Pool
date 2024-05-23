package com.pool.testsRA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.ResponseDto;
import com.pool.dto.user.NewUserDto;
import com.pool.dto.user.UserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class TestBase {

    public static final String EMAIL = "ushakov_test@mail.com";
    public static final String EMAIL_INVALID = "@mail.com";
    public static final String PASSWORD = "Pass12345!";
    public static final String PASSWORD_INVALID = "Pass123455";
    public static final String MESSAGE = "Login successful";
    public static final String SESSION_ID = "JSESSIONID";

    String n = "1";
    public NewUserDto register = NewUserDto.builder()
            .firstName("Bruce")
            .lastName("Wayne")
            .email("test" + n + "@mail.com")
            .phoneNumber("+11234567890")
            .password("Pass12345!")
            .build();

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api";
    }

    // Получение идентификатора сессии
    public static Cookies getCookiesForLogin() {
        return given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .extract().response().detailedCookies();
    }
    public static Cookies getCookiesForLogin(String email, String password) {
        return given()
                .contentType(ContentType.URLENC)
                .formParam("username", email)
                .formParam("password", password)
                .when()
                .post("/login")
                .then()
                .extract().response().detailedCookies();
    }

    // Печать в красивом формате JSON
    protected static void printJson(Object dto) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonResponse = gson.toJson(dto);

        // Вывод JSON-ответа с отступами и новыми строками
        System.out.println(jsonResponse);
    }

    // Регистрация нового пользователя с предопределёнными данными
    public UserDto registerNewUser() {
        return given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("users/register")
                .then()
                .extract().response().as(UserDto.class);
    }

    // Регистрация нового пользователя с указанным email
    public UserDto registerNewUser(String email) {
        NewUserDto customRegister = NewUserDto.builder()
                .firstName("Bruce")
                .lastName("Wayne")
                .email(email)
                .phoneNumber("+11234567890")
                .password("Pass12345!")
                .build();

        return given()
                .contentType(ContentType.JSON)
                .body(customRegister)
                .when()
                .post("users/register")
                .then()
                .extract().response().as(UserDto.class);
    }



    public UserDto createNewUserAndLogin(String email){
        NewUserDto user = NewUserDto.builder()
                .firstName("Bruce")
                .lastName("Wayne")
                .email(email)
                .phoneNumber("+11234567890")
                .password("Pass12345!")
                .build();

        UserDto emailRequest =  given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("users/register")
                .then()
                .extract().response().as(UserDto.class);

        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("username", user.getEmail())
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .extract().response().as(ResponseDto.class);

        return emailRequest;
    }
}
