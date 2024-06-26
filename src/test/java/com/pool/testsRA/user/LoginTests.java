package com.pool.testsRA.user;


import com.pool.dto.ResponseDto;
import com.pool.dto.user.UserDto;
import com.pool.testsRA.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;



public class LoginTests extends TestBase {


    @Test
    public void loginSuccessTest(){
        //UserDto newUser = registerNewUser(EMAIL_USER);
        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(ResponseDto.class);
        //deleteNewUser(newUser);
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
    }
    
    
}
