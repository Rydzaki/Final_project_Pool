package com.pool.testsRA;

import com.google.gson.Gson;
import com.pool.dto.RequestDto;
import com.pool.dto.UserDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserTests extends TestBase {

    int id = 9;

    UserDto userUpdate = UserDto.builder()
            .firstName("Bat")
            .lastName("Man")
            .role("User")
            .phoneNumber("+0987654321")
            .build();

    RequestDto auth = RequestDto.builder()
            .username(EMAIL)
            .password(PASSWORD)
            .build();

    @Test
    public void updateUserByIdSuccessTest() {
        // Аутентификация пользователя для получения доступа к системе
        given()
                .contentType(ContentType.URLENC)
                .formParam("username", auth.getUsername())
                .formParam("password", auth.getPassword())
                .when()
                .post("/login")
                .then()
                .assertThat()
                .statusCode(200);

        // Преобразование объекта в JSON с помощью библиотеки GSON
        Gson gson = new Gson();
        String jsonBody = gson.toJson(userUpdate);

        // Отправка PUT-запроса для обновления данных пользователя
        given()
                .auth().basic(EMAIL, PASSWORD)
                .contentType(ContentType.URLENC)
                .body(jsonBody)
                .when()
                .put("/users/" + id)
                .then()
                .assertThat()
                .statusCode(200) // Проверяем успешное обновление
                .body("firstName", equalTo(userUpdate.getFirstName()))
                .body("lastName", equalTo(userUpdate.getLastName()))
                .body("role", equalTo(userUpdate.getRole()))
                .body("phoneNumber", equalTo(userUpdate.getPhoneNumber()));

        // Проверяем, что данные пользователя действительно обновлены
        given()
                .auth().basic(EMAIL, PASSWORD)
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstName", equalTo(userUpdate.getFirstName()))
                .body("lastName", equalTo(userUpdate.getLastName()))
                .body("role", equalTo(userUpdate.getRole()))
                .body("phoneNumber", equalTo(userUpdate.getPhoneNumber()));
    }
}
