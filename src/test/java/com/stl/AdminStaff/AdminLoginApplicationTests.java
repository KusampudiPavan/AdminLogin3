package com.stl.AdminStaff;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;


@SuppressWarnings("unused")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminLoginApplicationTests {

    
    @Test
    @Order(2)
    public void testAdminLogin() {
        String jsonbody= "{\"email\" : \"admin@gmail.com\",\"password\" : \"admin\"}";
        String token=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonbody)
                .when()
                .post("http://localhost:8085/Admin/authenticatetoken")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
    
   
}
