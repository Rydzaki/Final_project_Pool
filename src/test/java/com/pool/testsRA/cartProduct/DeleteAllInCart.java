package com.pool.testsRA.cartProduct;

import com.pool.dto.cartProduct.CartProductDto;
import com.pool.dto.cartProduct.IdManager;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteAllInCart extends TestBase {

    private static final int CART_ID = 238;
    private static List<Integer> idCP = Collections.synchronizedList(new ArrayList<>()); // Thread-safe list

    @BeforeMethod
    public void setupCartProductIdList() {
        if (idCP.isEmpty()) {
            int currentId = IdManager.readCurrentId();
            idCP.add(currentId);
        }
    }

    @AfterMethod
    public void updateIdCartProduct() {
        synchronized (idCP) {
            int lastValue = idCP.get(idCP.size() - 1);
            int newValue = lastValue + 1;
            idCP.add(newValue);
            IdManager.writeCurrentId(newValue); // Сохранение нового значения в файл
        }
        System.out.println("Updated ID list: " + idCP);
    }

    @Test
    public void testDeleteAllInCart() {
        int currentId;

        synchronized (idCP) {
            currentId = idCP.get(idCP.size() - 1);
        }

        CartProductDto responseDeleteAll = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin("testForCartProduct@mail.com", PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .delete("/cart/" + CART_ID + "/cart-products/" + currentId)
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
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin("testForCartProduct@mail.com", PASSWORD).get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(productToCart)
                .when()
                .post("/cart/" + CART_ID + "/products")
                .then()
                .extract().response().as(CartProductDto.class);
    }
}
