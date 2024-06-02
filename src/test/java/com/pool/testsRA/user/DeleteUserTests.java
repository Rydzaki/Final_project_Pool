package com.pool.testsRA.user;

import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends TestBase {

    @Test
    public void deleteUserByIdSuccessTest() {

        UserDto newUser = registerNewUser("testDelete@mail.com");
        Integer idNewUser = newUser.getId();
        System.out.println(idNewUser);

        UserDto responseDelete = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .delete("/users/" + idNewUser)
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response().as(UserDto.class);

        printJson(responseDelete);
    }
}
