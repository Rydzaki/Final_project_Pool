package com.pool.testsRA.cartProduct;

import com.pool.dto.cartProduct.CartProductDto;
import com.pool.dto.orderProductDto.OrderCartProductDto;
import com.pool.dto.product.NewProductDto;
import com.pool.dto.product.ProductDto;
import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteAllInCart extends TestBase {


    @Test
    public void testDeleteAllInCart(){

        UserDto newUser = registerNewUser("testForCartProduct@mail.com");
        NewProductDto newProduct = createNewProduct();
        CartProductDto newCartProduct = createCartProduct(newUser, newProduct);

        CartProductDto responseDeleteAll = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .delete("/cart/" + newCartProduct.getCartId() +"/cart-products/"+ newCartProduct.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(newCartProduct.getId()))
                .body("cartId", equalTo(newCartProduct.getCartId()))
                .body("productId", equalTo(newCartProduct.getProductId()))
                .body("quantity", equalTo(newCartProduct.getQuantity()))
                .body("productName", equalTo(newCartProduct.getProductName()))
                .extract().response().as(CartProductDto.class);

        printJson(responseDeleteAll);

        deleteNewUser(newUser);



}}
