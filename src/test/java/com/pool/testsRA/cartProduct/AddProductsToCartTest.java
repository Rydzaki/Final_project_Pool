package com.pool.testsRA.cartProduct;

import com.pool.dto.cartProduct.CartProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddProductsToCartTest extends TestBase {

    private Integer cartId = 5; //всегда новому пользователю +1 от текущего числа

    int index = 5;
    @BeforeMethod
    public void precondition(){
        createNewUserAndLogin("test" + index + "@mail.com");
    }

    @Test
    public void addToCartPositiveTest() {
        CartProductDto productToCart = CartProductDto.builder()
                .productId(1)
                .quantity(1)
                .build();

        CartProductDto responseCartProduct = given()
            .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin(("test" + index + "@mail.com"), PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(productToCart) // Отправка нового продукта в теле запроса
                .when()
                .post("/cart/"+cartId +"/products")
                .then()
                .assertThat()
                .statusCode(200)
                .body("productId", equalTo(productToCart.getProductId()))
                .body("quantity", equalTo( productToCart.getQuantity())) //
                .extract().response().as(CartProductDto.class);

        printJson(responseCartProduct);


    }


    // необходимо руками создать БД cart_product, cart создается автоматически новому пользователю
//    @Test
//    public void addToCartPositiveTest() {
//        CartProductDto productToCart = CartProductDto.builder()
//                .productId(2)
//                .quantity(1)
//                .build();
//
//
//        CartProductDto responseCartProduct = given()
//                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
//                .contentType(ContentType.JSON)
//                .body(productToCart) // Отправка нового продукта в теле запроса
//                .when()
//                .post("/cart/"+cartId +"/products")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body("productId", equalTo(productToCart.getProductId()))
//                .body("quantity", equalTo( productToCart.getQuantity())) //
//                .extract().response().as(CartProductDto.class);
//
//        printJson(responseCartProduct);
//
//
//    }




}
