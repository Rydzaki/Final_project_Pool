package com.pool.testsRA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pool.dto.ResponseDto;
import com.pool.dto.cartProduct.CartProductDto;
import com.pool.dto.orders.NewOrdersDto;
import com.pool.dto.orders.OrderDto;
import com.pool.dto.product.NewProductDto;
import com.pool.dto.product.ProductDto;
import com.pool.dto.user.NewUserDto;
import com.pool.dto.user.UserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.testng.annotations.BeforeMethod;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestBase {

    static Instant now = Instant.now();

    public static final String EMAIL = "ushakov_test@mail.com";
    public static final String EMAIL_INVALID = "@mail.com";
    public static final String PASSWORD = "Pass12345!";
    public static final String PASSWORD_INVALID = "Pass123455";
    public static final String SESSION_ID = "JSESSIONID";
    public static final String DATE_NOW = DateTimeFormatter.ISO_INSTANT.format(now);

    String n = "1";
    public NewUserDto register = NewUserDto.builder()
            .firstName("Bruce")
            .lastName("Wayne")
            .email("test" + n + "@mail.com")
            .phoneNumber("+11234567890")
            .password("Pass12345!")
            .build();

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "http://localhost:8080";
        //RestAssured.baseURI = "https://cohort-34-pool-app-unpfj.ondigitalocean.app";
        RestAssured.basePath = "/api";
    }

    // Получение идентификатора сессии
    public static Cookies getCookiesForLogin() {
        return given()
                .contentType(ContentType.URLENC)
                .formParam("username", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .extract().response().detailedCookies();
    }

    public static Cookies getCookiesForLogin(String email, String password) {
        return given()
                .contentType(ContentType.URLENC)
                .formParam("username", email)
                .formParam("password", password)
                .when()
                .post("/login")
                .then()
                .extract().response().detailedCookies();
    }

    // Печать в красивом формате JSON
    protected static void printJson(Object dto) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonResponse = gson.toJson(dto);

        // Вывод JSON-ответа с отступами и новыми строками
        System.out.println(jsonResponse);
    }

    // Регистрация нового пользователя с предопределёнными данными
    public UserDto registerNewUser() {
        return given()
                .contentType(ContentType.JSON)
                .body(register)
                .when()
                .post("users/register")
                .then()
                .extract().response().as(UserDto.class);
    }

    public UserDto registerNewUser(String email) {
        NewUserDto customRegister = NewUserDto.builder()
                .firstName("Bruce")
                .lastName("Wayne")
                .email(email)
                .phoneNumber("+11234567890")
                .password("Pass12345!")
                .build();

        return given()
                .contentType(ContentType.JSON)
                .body(customRegister)
                .when()
                .post("users/register")
                .then()
                .extract().response().as(UserDto.class);
    }


    public UserDto createNewUserAndLogin(String email) {
        NewUserDto user = NewUserDto.builder()
                .firstName("Bruce")
                .lastName("Wayne")
                .email(email)
                .phoneNumber("+11234567890")
                .password("Pass12345!")
                .build();

        UserDto emailRequest = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("users/register")
                .then()
                .extract().response().as(UserDto.class);

        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("username", user.getEmail())
                .formParam("password", PASSWORD)
                .when()
                .post("/login")
                .then()
                .extract().response().as(ResponseDto.class);

        return emailRequest;
    }

    public void loginSuccessTest(String email, String password) {
        ResponseDto dto = given()
                .contentType(ContentType.URLENC)
                .formParam("username", email)
                .formParam("password", password)
                .when()
                .post("/login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .as(ResponseDto.class);

        System.out.println(dto.getMessage());
    }

    public CartProductDto addToCart(int cartId, int productId, int quantity) {
        CartProductDto productToCart = CartProductDto.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        CartProductDto responseCartProduct = given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(productToCart) // Отправка нового продукта в теле запроса
                .when()
                .post("/cart/" + cartId + "/products")
                .then()
                .assertThat()
                .statusCode(200)
                .body("productId", equalTo(productToCart.getProductId()))
                .body("quantity", equalTo(productToCart.getQuantity()))
                .extract()
                .response()
                .as(CartProductDto.class);

        printJson(responseCartProduct);
        return responseCartProduct;

    }

    public NewProductDto createNewProduct() {
        NewProductDto newProduct = NewProductDto.builder()
                .title("HCL")
                .price(100)
                .category("Chemistry")
                .build();
        return given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(newProduct)
                .when()
                .post("/products/")
                .then()
                .extract().response().as(NewProductDto.class);


    }

    public ProductDto deleteProduct(Integer idProduct){
        return given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .when()
                .delete("/products/" + idProduct)
                .then()
                .extract().response().as(ProductDto.class);
    }

    public OrderDto createNewOrder() {

        NewProductDto newProduct = createNewProduct();
        Integer idProduct = newProduct.getId();
        NewOrdersDto newOrder = NewOrdersDto.builder()
                .userId(5)
                .productId(idProduct)
                .itemsCount(1)
                .date(DATE_NOW)
                .build();

        return given()
                .cookie(new Cookie.Builder(SESSION_ID, getCookiesForLogin().get(SESSION_ID).getValue()).build())
                .contentType(ContentType.JSON)
                .body(newOrder)
                .when()
                .post("/orders")
                .then()
                .extract().response().as(OrderDto.class);

    }

}
