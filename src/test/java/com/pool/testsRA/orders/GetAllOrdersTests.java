package com.pool.testsRA.orders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.pool.dto.orders.OrdersDto;
import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import com.pool.testsRA.ZonedDateTimeAdapter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllOrdersTests extends TestBase {
    @Test
    public void getAllOrdersPositiveTest() {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();

            String jsonResponse = given()
                    .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/orders")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .extract().response().asString();

            OrdersDto[] ordersArray = gson.fromJson(jsonResponse, OrdersDto[].class);
            List<OrdersDto> responseOrders = Arrays.asList(ordersArray);

            printJson(responseOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
