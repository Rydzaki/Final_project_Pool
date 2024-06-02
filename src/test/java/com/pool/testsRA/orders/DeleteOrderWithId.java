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

import static com.pool.testsRA.TestBase.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteOrderWithId extends TestBase {


    @Test
    public void deleteGetOrderWithId() {
        OrderDto newOrder = createNewOrder();
        Integer orderId = newOrder.getId();

        OrderDto responseOrder = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .delete("/orders/" + orderId)//TODO add orderId
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .extract().response().as(OrderDto.class);
        printJson(responseOrder);

    }
}
