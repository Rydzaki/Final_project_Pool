package com.pool.testsRA.cartProduct;

import com.pool.dto.cartProduct.CartProductDto;
import com.pool.dto.orderProductDto.OrderCartProductDto;
import com.pool.dto.product.NewProductDto;
import com.pool.dto.product.ProductDto;
import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAllInCart extends TestBase {

    private static Integer idCartProduct = 55;//TODO

    @AfterMethod
    public void updateIdCartProduct() {
        idCartProduct += 1;
    }

    @Test()
    public void testDeleteAllInCart() {

        CartProductDto responseDeleteAll = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin("testForCartProduct@mail.com", PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .delete("/cart/" + 238 + "/cart-products/" + idCartProduct)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(CartProductDto.class);

        printJson(responseDeleteAll);


        CartProductDto productToCart = CartProductDto.builder()
                .productId(1)
                .quantity(1)
                .build();

        CartProductDto responseCartProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin(("testForCartProduct@mail.com"), PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(productToCart) // Отправка нового продукта в теле запроса
                .when()
                .post("/cart/" + 238 + "/products")
                .then()
                .extract().response().as(CartProductDto.class);

        //deleteNewUser(newUser);


    }
}
