package com.pool.testsRA;


import com.pool.dto.RequestDto;
import com.pool.dto.ResponseDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTests extends TestBase {
    
    RequestDto auth = RequestDto.builder()
            .username(EMAIL)
            .password(PASSWORD)
            .build();
    
    @Test
    public void loginSuccessTest(){
        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("username", auth.getUsername())
                .formParam("password", auth.getPassword())
                /*.body(auth)*/
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }
    
}
