package com.pool.testsRA.orders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.orders.NewOrdersDto;
import com.pool.dto.orders.OrderDto;
import com.pool.testsRA.TestBase;
import com.pool.testsRA.ZonedDateTimeAdapter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;

import static com.pool.testsRA.TestBase.SESSION_ID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateOrdersWithId extends TestBase {

    @Test
    public void updateOrderWithIdTests() {
        // Установим дату в виде строки в формате ISO 8601
        String date = "2024-05-19T19:30:18.591+00:00";
        Integer ordersId=2;

        NewOrdersDto newOrder = NewOrdersDto.builder()
                .userId(ordersId)
                .itemsCount(3)
                .date(date) // Дата передается как строка
                .build();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        // Преобразуем объект NewOrdersDto в JSON строку
        String jsonRequest = gson.toJson(newOrder);
        System.out.println("Request JSON: " + jsonRequest); // Печатаем JSON для проверки

        // Отправляем PUT запрос и проверяем ответ
        OrderDto responseOrder = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(jsonRequest) // Передаем тело запроса
                .when()
                .put("/orders/" + ordersId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("userId", equalTo(newOrder.getUserId()))
                .body("itemsCount", equalTo(newOrder.getItemsCount()))
                .body("date", equalTo(newOrder.getDate())) // Проверяем дату
                .extract().response().as(OrderDto.class);

        // Печатаем JSON ответа
        printJson(responseOrder);
    }
}
