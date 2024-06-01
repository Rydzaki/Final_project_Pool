package com.pool.testsRA.orders;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.orders.NewOrdersDto;
import com.pool.dto.orders.OrderDto;

import com.pool.dto.product.NewProductDto;
import com.pool.dto.product.ProductDto;
import com.pool.testsRA.TestBase;
import com.pool.testsRA.ZonedDateTimeAdapter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.openqa.selenium.devtools.v85.page.Page;
import org.testng.annotations.Test;


import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class CreateNewOrdersTests extends TestBase {


    @Test
    public void createNewOrderTests() {

        NewProductDto newProduct = createNewProduct();
        Integer idProduct = newProduct.getId();

        NewOrdersDto newOrder = NewOrdersDto.builder()
                .userId(5)
                .productId(idProduct)
                .itemsCount(1)
                .date(DATE_NOW)
                .build();
        try {
            OrderDto responseOrder = given()
                    .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                    .contentType(ContentType.JSON)
                    .body(newOrder)
                    .log().all()
                    .when()
                    .post("/orders")
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(201)
                    .body("userId", notNullValue())
                    .body("productId", equalTo(newOrder.getUserId()))
                    .body("itemsCount", equalTo(newOrder.getItemsCount()))
                    .extract().response().as(OrderDto.class);

            printJson(responseOrder);
            deleteProduct(idProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
