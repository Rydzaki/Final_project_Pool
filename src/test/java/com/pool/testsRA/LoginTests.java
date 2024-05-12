package com.pool.testsRA;


import com.pool.dto.EmailAndPassword;
import com.pool.dto.ResponseDto;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTests extends TestBase {
    
    EmailAndPassword auth = EmailAndPassword.builder()
            .username(EMAIL)
            .password(PASSWORD)
            .build();
    
    @Test
    public void loginSuccessTest(){
        ResponseDto dto = given()
                .body(auth)
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }
    
}
