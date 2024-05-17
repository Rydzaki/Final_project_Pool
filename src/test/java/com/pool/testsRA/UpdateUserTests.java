package com.pool.testsRA;

import com.google.gson.Gson;
import com.pool.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTests extends TestBase {

    public Integer idUser = 3;

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

        UserDto responseUpdate = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .body(jsonBodyUpdateUser)
                .contentType(ContentType.JSON)
                .when()
                .put("/users/" + idUser)
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstName", equalTo(userUpdate.getFirstName()))
                .body("lastName", equalTo(userUpdate.getLastName()))
                .body("role", equalTo(userUpdate.getRole()))
                .body("phoneNumber", equalTo(userUpdate.getPhoneNumber()))
                .extract().response().as(UserDto.class);

        printJson(responseUpdate);
    }
}
