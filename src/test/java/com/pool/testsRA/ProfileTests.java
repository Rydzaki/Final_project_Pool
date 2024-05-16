package com.pool.testsRA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.ResponseDto;
import com.pool.dto.UserDto;
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


    @Test
    public void profileNegativeTest() {
        ResponseDto dto = given()
                .get("/users/profile")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ResponseDto.class);

        System.out.println(dto.getMessage());
    }

   @Test
    public void getProfilePositiveTest() {

        String sessionValue = getCookiesForLogin().get(SESSIONID).getValue();

        // Использование значения сессии для доступа к профилю пользователя
        UserDto dto = given()
                .cookie(new Cookie.Builder(SESSIONID, sessionValue).build()) // Установка сессии из куки
                .when()
                .get("/users/profile")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(UserDto.class);

       // Создание Gson с отступами и новыми строками
       Gson gson = new GsonBuilder().setPrettyPrinting().create();
       String jsonResponse = gson.toJson(dto);

       // Вывод JSON-ответа с отступами и новыми строками
       System.out.println(jsonResponse);
    }

}
