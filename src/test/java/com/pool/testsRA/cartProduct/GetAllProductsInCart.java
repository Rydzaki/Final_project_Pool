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
        loginSuccessTest("userOleg@mail.com","Qwerty007!"); //Антон, здесь напиши данные из своей дб
        addToCart(3,1,1);
    }

    @Test
    public void getAllProductsInCartTest() {
        List<OrderCartProductDto> orderProductDtoList = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
              //  .log().all()  // Логирование запроса
                .when()
                .get("/cart/" + cartId )
                .then()
                //.log().all()  // Логирование ответа
                .assertThat().statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList(".", OrderCartProductDto.class);

        printJson(orderProductDtoList);


    }
}
