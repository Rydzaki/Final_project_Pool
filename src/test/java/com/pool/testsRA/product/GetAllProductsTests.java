package com.pool.testsRA.product;

import com.pool.dto.product.ProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class GetAllProductsTests extends TestBase {

    @Test
    public void getAllProductsTest() {

        List<ProductDto> responseProducts = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin(EMAIL_USER, PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .body("id", notNullValue())
                .extract().response().jsonPath().getList(".", ProductDto.class);
        printJson(responseProducts);

    }

}
