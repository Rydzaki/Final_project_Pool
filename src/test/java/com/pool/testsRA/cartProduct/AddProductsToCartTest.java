package com.pool.testsRA.cartProduct;

import com.pool.dto.cartProduct.CartProductDto;
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

public class AddProductsToCartTest extends TestBase {


    @Test
    public void addToCartPositiveTest() {
        UserDto newUser = registerNewUser("testForAddProductToCart@mail.com");
        Integer idNewUser = newUser.getId();
        NewProductDto newProduct = createNewProduct();

        CartProductDto productToCart = CartProductDto.builder()
                .productId(createNewProduct().getId())
                .quantity(1)
                .build();

        CartProductDto responseCartProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin((newUser.getEmail()), PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(productToCart) // Отправка нового продукта в теле запроса
                .when()
                .post("/cart/" + idNewUser + "/products")
                .then()
                .assertThat()
                .statusCode(200)
                .body("productId", equalTo(productToCart.getProductId()))
                .body("quantity", equalTo(productToCart.getQuantity())) //
                .extract().response().as(CartProductDto.class);

        printJson(responseCartProduct);
        deleteNewUser(newUser);
        deleteProduct(newProduct.getId());
    }
}