package com.pool.testsRA.orders;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.orders.OrdersDto;
import com.pool.testsRA.TestBase;
import com.pool.testsRA.ZonedDateTimeAdapter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class CreateNewOrdersTests extends TestBase {

    @Test
    public void createNewOrderTests() {
        // Создаем объект OrdersDto с примерными данными
        ZonedDateTime date = ZonedDateTime.parse("2024-05-19T17:30:18.591Z");

        OrdersDto newOrder = OrdersDto.builder()
                .userId(5)
                .summa(100.50)
                .itemsCount(3)
                .date(date)
                .build();

        // Создаем объект Gson с зарегистрированным адаптером ZonedDateTime
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .setPrettyPrinting()
                .create();

        // Преобразуем объект OrdersDto в JSON строку
        String jsonRequest = gson.toJson(newOrder);
        System.out.println("Request JSON: " + jsonRequest);

        // Отправляем POST запрос для создания нового заказа
        OrdersDto responseOrder = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .post("/orders/")
                .then()
                .assertThat()
                .statusCode(201)
                .body("id", notNullValue())
                .body("userId", equalTo(newOrder.getUserId()))
                .body("summa", equalTo((float) newOrder.getSumma()))
                .body("itemsCount", equalTo(newOrder.getItemsCount()))
                .body("date", notNullValue())
                .extract().response().as(OrdersDto.class);

        // Печатаем JSON ответа
        printJson(responseOrder);
    }


}
