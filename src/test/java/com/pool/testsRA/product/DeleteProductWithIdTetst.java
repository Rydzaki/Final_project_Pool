package com.pool.testsRA.product;

import com.pool.dto.product.ProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductWithIdTetst extends TestBase {

    private Integer idProduct = 3;

    @Test
    public void getProductByIdSuccessTest() {

        ProductDto expectedProduct = ProductDto.builder()
                .id(idProduct)
                .title("услуга/химия")
                .price(34.99)
                .category("услуга/химия")
                .build();

        ProductDto responseProduct = given()
                .when()
                .delete("/products/" + idProduct)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(expectedProduct.getId()))
                .body("title", equalTo(expectedProduct.getTitle()))
                .body("price", equalTo((float) expectedProduct.getPrice()))
                .body("category", equalTo(expectedProduct.getCategory()))
                .extract().response().as(ProductDto.class);

        printJson(responseProduct);

        printJson(responseProduct);
    }
}
