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

    private static Integer idCartProduct = 44;
    @Test
    public void getProductByIdSuccessTest() {

//        //UserDto newUser = registerNewUser("testForUpdateProductsinCart@mail.com");
//        //NewProductDto newProduct = createNewProduct();
//        CartProductDto newCartProduct = createCartProduct(newUser, newProduct);

        OrderCartProductDto updateQuantity = OrderCartProductDto.builder()
                .id(1)
                .quantity(100)
                .build();

        OrderCartProductDto responseProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin("testForUpdateProductsinCart@mail.com", PASSWORD).get(SESSION_ID).getValue()).build())
                .body(updateQuantity)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .put("/cart/" + 240 +"/cart-products/"+44)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().as(OrderCartProductDto.class);

        printJson(responseProduct);

    }
}



