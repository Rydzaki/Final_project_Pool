package com.pool.testsRA.cartProduct;

import com.pool.dto.cartProduct.CartProductDto;
import com.pool.dto.orderProductDto.OrderCartProductDto;
import com.pool.dto.product.NewProductDto;
import com.pool.dto.user.NewUserDto;
import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class GetAllProductsInCart extends TestBase {


    @Test
    public void getAllProductsInCartTest() {

        List<OrderCartProductDto> orderProductDtoList = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin("testForAllProductsinCart@mail.com", PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/cart/" + 237)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList(".", OrderCartProductDto.class);

        printJson(orderProductDtoList);
        
    }
}
