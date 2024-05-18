package com.pool.testsRA.product;

import com.google.gson.Gson;
import com.pool.dto.product.ProductDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateProductTests extends TestBase {

    private Integer idProduct = 2;

    @Test
    public void getProductByIdSuccessTest() {

        ProductDto productUpdate = ProductDto.builder()
                .id(idProduct)
                .title("Update")
                .price(100.99)
                .category("услуга/химия")
                .build();

        Gson gson = new Gson();
        String jsonBodyUpdateProduct = gson.toJson(productUpdate);



        ProductDto responseProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .body(jsonBodyUpdateProduct)
                .contentType(ContentType.JSON)
                .when()
                .put("/products/" + idProduct)
                .then()
                .assertThat()
                .statusCode(200)

                .body("id", equalTo(productUpdate.getId()))
                .body("title", equalTo(productUpdate.getTitle()))
                .body("price", equalTo((float) productUpdate.getPrice()))
                .body("category", equalTo(productUpdate.getCategory()))
                .extract().response().as(ProductDto.class);

        printJson(responseProduct);
    }
}
