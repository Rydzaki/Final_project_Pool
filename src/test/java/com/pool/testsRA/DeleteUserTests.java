package com.pool.testsRA;

import com.google.gson.Gson;
import com.pool.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserTests extends TestBase{

    Integer userId;
    @BeforeMethod
    public void precondition(){
        UserDto user = given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("users/register")
                .then()
                .extract().response().as(UserDto.class);
        userId = user.getId();
    }

    public Integer idUserForDelete = userId;

    @Test
    public void updateUserByIdSuccessTest() {
        UserDto userUpdate = UserDto.builder()
                .firstName("Jo")
                .lastName("Bill")
                .role("ADMIN")
                .phoneNumber("+0987654321")
                .build();

        Gson gson = new Gson();
        String jsonBodyUpdateUser = gson.toJson(userUpdate);

        UserDto responseDelete = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .body(jsonBodyUpdateUser)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/" + idUserForDelete)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(UserDto.class);

        printJson(responseDelete);
    }
}

