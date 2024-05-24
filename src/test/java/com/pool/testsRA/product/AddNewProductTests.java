package com.pool.testsRA.product;

import com.pool.dto.product.NewProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddNewProductTests extends TestBase {

    @Test
    public void testAddNewProduct() {

        NewProductDto newProduct = NewProductDto.builder()

                .title("HCL")
                .price(100)
                .category("Chemistry")
                .build();



        NewProductDto responseProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(newProduct) // Отправка нового продукта в теле запроса
                .when()
                .post("/products/")
                .then()
                .assertThat()
                .statusCode(201)
                .body("title", equalTo(newProduct.getTitle()))
                .body("price", equalTo((float) newProduct.getPrice())) // Преобразование double к float
                .body("category", equalTo(newProduct.getCategory()))
                .extract().response().as(NewProductDto.class);

        printJson(responseProduct);


    }
}