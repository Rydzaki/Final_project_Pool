package com.pool.testsRA;

import com.google.gson.Gson;
import com.pool.dto.RequestDto;
import com.pool.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserTests extends TestBase {

    Integer id = 9;

    UserDto userUpdate = UserDto.builder()
            .firstName("Bat")
            .lastName("Man")
            .role("User")
            .phoneNumber("+0987654321")
            .build();

    @Test
    public void updateUserByIdSuccessTest() {

        String sessionValue = getCookiesForLogin().get(SESSIONID).getValue();
        // Преобразование объекта в JSON с помощью библиотеки GSON
        Gson gson = new Gson();
        String jsonBodyUpdateUser = gson.toJson(userUpdate);

        // Отправка PUT-запроса для обновления данных пользователя
        given()
                .cookie(new Cookie.Builder(SESSIONID, sessionValue).build())
                .contentType(ContentType.JSON)
                .body(jsonBodyUpdateUser)
                .when()
                .put("/users/" + id)
                .then()
                .assertThat()
                .statusCode(200) // Проверяем успешное обновление
                .body("firstName", equalTo(userUpdate.getFirstName()))
                .body("lastName", equalTo(userUpdate.getLastName()))
                .body("role", equalTo(userUpdate.getRole()))
                .body("phoneNumber", equalTo(userUpdate.getPhoneNumber()));
    }
}
