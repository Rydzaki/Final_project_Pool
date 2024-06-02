package com.pool.testsRA.user;

import com.pool.dto.user.NewUserDto;
import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterTests extends TestBase {


    @Test(priority = 1)
    public void registerSuccessTest() {
        UserDto user = given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response().as(UserDto.class);
        deleteNewUser(user);
    }

    @Test(priority = 2)
    public void registrationWithExistingEmailTest() {
        UserDto newUser = createNewUserAndLogin(register.getEmail());
        UserDto user = given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(409)
                .extract().response().as(UserDto.class);
        deleteNewUser(newUser);
    }

    @Test(priority = 3)
    public void registrationWithErrorEmailTest() {

        NewUserDto errorMail = NewUserDto.builder()
                .email("@mail.com")
                .build();

        UserDto user = given()
                .contentType(ContentType.JSON)
                .body(errorMail)
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400).extract().response().as(UserDto.class);
    }

}
