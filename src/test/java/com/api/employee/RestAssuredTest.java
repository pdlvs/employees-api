package com.api.employee;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RestAssuredTest {

    @BeforeAll
    @DirtiesContext
    public static void setup(){
        baseURI = "http://localhost:8080";
    }

    @Test
    public void getEmployeeTest(){
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/employees")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void postEmployeeTest(){
        String requestBody = "{\n" +
                "    \"name\": \"João\",\n" +
                "    \"role\": \"QA\"\n" +
                "}";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/employees")
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("João", response.jsonPath().getString("name"));
        Assertions.assertEquals("QA", response.jsonPath().getString("role"));
    }

    @Test
    public void putEmployeeTest(){
        String requestBody = "{\n" +
                "    \"name\": \"Pedro\",\n" +
                "    \"role\": \"DEV\"\n" +
                "}";
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/employees/1")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Pedro", response.jsonPath().getString("name"));
        Assertions.assertEquals("DEV", response.jsonPath().getString("role"));
    }

    @Test
    public void deleteEmployeeTest(){
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .delete("/employees/1")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());

    }
}
