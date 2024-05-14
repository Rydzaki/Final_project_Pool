package com.pool.testsRA;

import com.pool.dto.RequestDto;
import com.pool.dto.ResponseDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;

public class ProfileTests extends TestBase {


    @Test
    public void profileNegativeTest() {
        ResponseDto dto = given()
                .get("/users/profile")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }


//    @Test
//    public void profilePositiveTest() {
//        ResponseDto dto = given()
//                .contentType(ContentType.URLENC)
//                .formParam("username", EMAIL)
//                .formParam("password", PASSWORD)
//                .when()
//                .post("/login")
//                .then()
//                .get("/users/profile")
//                .then()
//                .assertThat().statusCode(200)
//                .extract().response().as(ResponseDto.class);
//
//        System.out.println(dto.getMessage());
//    }

    @Test
    public void profilePositiveTest() {
        // Получение значения сессии из куки
        Cookies cookies = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .extract().response().detailedCookies();

        // Извлечение значения сессии из куки
        String sessionValue = cookies.get("sessionCookieName").getValue();

        // Использование значения сессии для доступа к профилю пользователя
        ResponseDto dto = given()
                .cookie(new Cookie.Builder("sessionCookieName", sessionValue).build()) // Установка сессии из куки
                .when()
                .get("/users/profile")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);

        System.out.println(dto.getMessage());
    }

}
