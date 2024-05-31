package com.pool.testsRA.orders;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.orders.OrderDto;

import com.pool.testsRA.TestBase;
import com.pool.testsRA.ZonedDateTimeAdapter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;


import java.time.ZonedDateTime;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class CreateNewOrdersTests extends TestBase {


    @Test
    public void createNewOrderTests() {

        OrderDto newOrder = OrderDto.builder()
                .userId(3)
                .summa(25.77)
                .itemsCount(1)
                .date("2024-05-20T22:27:31.444Z")
                .build();


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .setPrettyPrinting()
                .create();


        String jsonRequest = gson.toJson(newOrder);

        OrderDto responseOrder = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(201)
                .body("id", notNullValue())
                .body("userId", equalTo(newOrder.getUserId()))
                .body("summa", equalTo((float) newOrder.getSumma()))
                .body("itemsCount", equalTo(newOrder.getItemsCount()))
                .extract().response().as(OrderDto.class);

        printJson(responseOrder);
    }


}
