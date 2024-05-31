package com.pool.testsRA.orders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.orders.NewOrdersDto;
import com.pool.dto.orders.OrderDto;
import com.pool.testsRA.TestBase;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateOrdersWithId extends TestBase {

    @Test
    public void updateOrderWithIdTests() {

        String date = "2024-05-19T19:30:18.591+00:00";
        Integer ordersId=2;

        NewOrdersDto newOrder = NewOrdersDto.builder()
                .userId(ordersId)
                .summa(2000)
                .itemsCount(3)
                .date(date)
                .build();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        String jsonRequest = gson.toJson(newOrder);
        System.out.println("Request JSON: " + jsonRequest);


        OrderDto responseOrder = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .put("/orders/" + ordersId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("userId", equalTo(newOrder.getUserId()))
                .body("summa", equalTo((float) newOrder.getSumma()))
                .body("itemsCount", equalTo(newOrder.getItemsCount()))
                .body("date", equalTo(newOrder.getDate()))
                .extract().response().as(OrderDto.class);

        printJson(responseOrder);
    }
}
