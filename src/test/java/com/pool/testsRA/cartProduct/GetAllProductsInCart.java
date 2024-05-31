package com.pool.testsRA.cartProduct;

import com.pool.dto.orderProductDto.OrderCartProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class GetAllProductsInCart extends TestBase {

private Integer cartId = 3;
    @BeforeMethod
    public void precondition(){
        loginSuccessTest("martaZ@gm.com","Pass12345!");
        addToCart(3,1,1);
    }

    @Test
    public void getAllProductsInCartTest() {
        List<OrderCartProductDto> orderProductDtoList = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .get("/cart/" + cartId )
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList(".", OrderCartProductDto.class);

        printJson(orderProductDtoList);


    }
}
