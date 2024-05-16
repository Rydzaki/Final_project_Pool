package com.pool.testsRA;


import com.pool.dto.RequestDto;
import com.pool.dto.ResponseDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;



public class LoginTests extends TestBase {

//    RequestDto auth = RequestDto.builder()
//            .username(EMAIL)
//            .password(PASSWORD)
//            .build();
//

    @Test
    public void loginSuccessTest(){
        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                /*.body(auth)*/
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);


        System.out.println(dto.getMessage());
    }

    @Test 
    public void loginWithWrongEmail(){
        ResponseDto error = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL_INVALID)
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ResponseDto.class);

        System.out.println(error.getMessage());
    }

    @Test
    public void loginWithWrongPassword(){
        ResponseDto error = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD_INVALID)
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ResponseDto.class);

        System.out.println(error.getMessage());
    }

    @Test
    public void loginWithoutEmail(){
        ResponseDto error = given()
                .contentType(ContentType.URLENC)
                .formParam("username", "")
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ResponseDto.class);

        System.out.println(error.getMessage());
    }
    @Test
    public void loginWithoutPassword(){
        ResponseDto error = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", "")
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ResponseDto.class);

        System.out.println(error.getMessage());
    }
    
    
}
