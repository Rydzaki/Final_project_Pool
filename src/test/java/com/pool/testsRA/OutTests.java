package com.pool.testsRA;

import com.pool.dto.ResponseDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OutTests extends TestBase {

    @Test
    public void logoutSuccessTest(){
        ResponseDto dto = given()
                .post("/logout")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }

    @Test
    public void logoutNegativeTest(){ // Странно что не передай в реквест все принимает
        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("errorData", "errorData")
                .post("/logout")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }


}
