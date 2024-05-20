package com.pool.testsRA.user;

import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends TestBase {

    UserDto newUser = registerNewUser("testDelete@mail.com");
    Integer userId = newUser.getId(); // получаем его ID


    @Test
    public void deleteUserByIdSuccessTest() {
        // Создаем нового пользователя с пометкой в email


        // Удаление пользователя
        UserDto responseDelete = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/" + userId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(UserDto.class);

        printJson(responseDelete);
    }

    @Test
    public void deleteUserByIdNegativeTest() {
        // Проверить, что пользователь удален
        given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + userId)
                .then()
                .assertThat()
                .statusCode(405); // Ожидаем, что пользователь не существует
    }
}