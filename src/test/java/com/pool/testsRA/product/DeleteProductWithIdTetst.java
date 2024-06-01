package com.pool.testsRA.product;

import com.pool.dto.product.NewProductDto;
import com.pool.dto.product.ProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductWithIdTetst extends TestBase {


    @Test
    public void getProductByIdSuccessTest() {

        NewProductDto newProduct = createNewProduct();
        Integer idProduct = newProduct.getId();

        ProductDto expectedProduct = ProductDto.builder()
                .id(newProduct.getId())
                .title(newProduct.getTitle())
                .price(newProduct.getPrice())
                .category(newProduct.getCategory())
                .build();

        ProductDto responseProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
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
    }
}
