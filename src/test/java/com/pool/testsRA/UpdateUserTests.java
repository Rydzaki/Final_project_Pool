package com.pool.testsRA;

import com.google.gson.Gson;
import com.pool.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class UpdateUserTests extends TestBase {

    Integer idUser = 9;

    UserDto userUpdate = UserDto.builder()
            .firstName("Bat")
            .lastName("Man")
            .role("User")
            .phoneNumber("+0987654321")
            .build();


    @Test
    public void updateUserByIdSuccessTest() {

        Gson gson = new Gson();
        String jsonBodyUpdateUser = gson.toJson(userUpdate);
        //System.out.println(jsonBodyUpdateUser);

        // Отправка PUT-запроса для обновления данных пользователя
        UserDto responseUpdate = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .body(jsonBodyUpdateUser)
                .contentType(ContentType.JSON)
                .when()
                .patch("/users/" + idUser)
                .then()
                .assertThat()
                .statusCode(200) // Проверяем успешное обновление
                .extract().response().as(UserDto.class);

        System.out.println(responseUpdate);

//                .body("firstName", equalTo(userUpdate.getFirstName()))
//                .body("lastName", equalTo(userUpdate.getLastName()))
//                .body("role", equalTo(userUpdate.getRole()))
//                .body("phoneNumber", equalTo(userUpdate.getPhoneNumber()));
    }


}
