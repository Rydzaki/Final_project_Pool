package com.pool.testsRA.cartProduct;

import com.google.gson.Gson;
import com.pool.dto.orderProductDto.OrderCartProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateProductsQuantity extends TestBase {

    private Integer cartId = 3;
    private Integer cartProductId = 12;

    @BeforeMethod
    public void precondition(){
        loginSuccessTest("userOleg@mail.com","Qwerty007!");
        addToCart(3,1,1);
    }

    @Test
    public void getProductByIdSuccessTest() {

        OrderCartProductDto updateQuantity = OrderCartProductDto.builder()
                .id(cartProductId)
                .quantity(2)
                .build();

        Gson gson = new Gson();
        String jsonBodyUpdateProduct = gson.toJson(updateQuantity);

        OrderCartProductDto responseProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .body(jsonBodyUpdateProduct)
                .contentType(ContentType.JSON)
                .when()
                .put("/cart/" + cartId +"/cart-products/"+ cartProductId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(OrderCartProductDto.class);

        printJson(responseProduct);
    }
}



