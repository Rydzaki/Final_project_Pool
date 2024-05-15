package com.pool.testsRA;

import com.pool.dto.RequestDto;
import com.pool.dto.ResponseDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.pool.testsRA.TestBase.EMAIL;
import static com.pool.testsRA.TestBase.PASSWORD;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;

public class ProfileTests extends TestBase {


    @BeforeMethod
    public void precondition(){
        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .post("/login")
                .then()
                .extract().response().as(ResponseDto.class);
    }


    @Test
    public void profileNegativeTest() {
        ResponseDto dto = given()
                .get("/users/profile")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }

    /*@Test
    public void profilePositiveTest() {
        ResponseDto dto = given()
                .header("username", EMAIL)
                .header("password", PASSWORD)
                *//*.contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .post("/login")
                .then()*//*
                .get("/users/profile")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }*/

    @Test
    public void profilePositiveTest() {
        ResponseDto log = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .post("/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);
        System.out.println(log.getMessage());



        // Выполнение GET-запроса для получения профиля пользователя
        ResponseDto dto = given()
               /* .header("username", EMAIL)
                .header("password", PASSWORD)*/
            .header("message", "D193BA76C1FC89996D10746FEF3EE9D8")
                .get("/users/profile")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);

        // Вывод сообщения из ответа
        System.out.println(dto.getMessage());
    }


  /* @Test
    public void profilePositiveTest() {


        Cookies cookies = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .extract().response().detailedCookies();

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
    }*/

}
