package com.pool.testsRA.orders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.orders.NewOrdersDto;
import com.pool.dto.orders.OrderDto;
import com.pool.dto.product.ProductDto;
import com.pool.testsRA.TestBase;
import com.pool.testsRA.ZonedDateTimeAdapter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderWithIdTests extends TestBase {

    private Integer ordersId = 1;

    @Test
            public void testGetOrderWithId() {
        NewOrdersDto newOrder = NewOrdersDto.builder()
                .userId(1)
                .summa(20)
                .itemsCount(3)
                .date("2024-05-19T19:30:18.591Z")
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .setPrettyPrinting()
                .create();

        // Преобразуем объект OrdersDto в JSON строку
        String jsonRequest = gson.toJson(newOrder);
        OrderDto responseOrder = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .get("/orders/" + ordersId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("userId", equalTo(newOrder.getUserId()))
                .body("summa", equalTo((float) newOrder.getSumma()))
                .body("itemsCount", equalTo(newOrder.getItemsCount()))
                .extract().response().as(OrderDto.class);

        // Печатаем JSON ответа
        printJson(responseOrder);



    }
}
