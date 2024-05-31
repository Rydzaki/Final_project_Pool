package com.pool.testsRA.user;

import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllUsersTests extends TestBase {

    @Test
    public void getAllUsersPositiveTest() {

        List<UserDto> listAllUsers = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin("ushakov_test@mail.com", PASSWORD).get(SESSION_ID).getValue()).build()) // Установка сессии из куки
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then()
                .assertThat().statusCode(200)
                .extract().response().jsonPath().getList( ".",UserDto.class);

        // Создание Json с отступами и новыми строками
        printJson(listAllUsers);
    }
}
