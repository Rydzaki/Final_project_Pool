package com.pool.testsRA.cartProduct;

import com.pool.dto.cartProduct.CartProductDto;
import com.pool.dto.orderProductDto.OrderCartProductDto;
import com.pool.dto.product.ProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAllInCart extends TestBase {
    private Integer cartId = 3;
    private Integer cartProductId = 14;

    @BeforeMethod
    public void precondition(){
        loginSuccessTest("userOleg@mail.com","Qwerty007!");
        addToCart(3,1,1);
    }

    @Test
    public void testDeleteAllInCart(){
        CartProductDto expectedResult = CartProductDto.builder()
                .id(cartProductId)
                .cartId(cartId)
                .productId(1)
                .quantity(1)
                .productName("услуга/химия")
                .build();


        CartProductDto responseDeleteAll = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .delete("/cart/" + cartId +"/cart-products/"+ cartProductId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(expectedResult.getId()))
                .body("cartId", equalTo(expectedResult.getCartId()))
                .body("productId", equalTo(expectedResult.getProductId()))
                .body("quantity", equalTo(expectedResult.getQuantity()))
                .body("productName", equalTo(expectedResult.getProductName()))
                .extract().response().as(CartProductDto.class);

        printJson(responseDeleteAll);


}}
