package com.pool.testsRA.cartProduct;

import com.google.gson.Gson;
import com.pool.dto.cartProduct.CartProductDto;
import com.pool.dto.orderProductDto.OrderCartProductDto;
import com.pool.dto.product.NewProductDto;
import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateProductsQuantity extends TestBase {

    @Test
    public void getProductByIdSuccessTest() {

        UserDto newUser = registerNewUser("testForUpdateProductsinCart@mail.com");
        NewProductDto newProduct = createNewProduct();
        CartProductDto newCartProduct = createCartProduct(newUser, newProduct);

        OrderCartProductDto updateQuantity = OrderCartProductDto.builder()
                .id(newProduct.getId())
                .quantity(100)
                .build();

        OrderCartProductDto responseProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .body(updateQuantity)
                .contentType(ContentType.JSON)
                .when()
                .put("/cart/" + newCartProduct.getCartId() +"/cart-products/"+ newCartProduct.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(OrderCartProductDto.class);

        printJson(responseProduct);
        deleteNewUser(newUser);
    }
}



