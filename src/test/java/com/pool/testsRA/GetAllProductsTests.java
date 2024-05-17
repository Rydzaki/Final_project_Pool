package com.pool.testsRA;

import com.pool.dto.ProductDto;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class GetAllProductsTests extends TestBase{

    @Test
            public void getAllProductsTest() {

        List<ProductDto> responseProducts = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue()) // Проверка, что id не null
                .extract().response().jsonPath().getList(".", ProductDto.class);
        printJson(responseProducts);

    }

}
