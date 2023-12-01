// Test generated by RoostGPT for test restAssuredTest using AI Type Azure Open AI and AI Model roost-gpt4-32k

// RoostTestHash=4940f5c55b

package com.team.RoostTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NobelPrizesGetTest {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeAll
    public void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL");
        
        requestSpec = with()
            .basePath("nobelPrizes")
            .contentType(ContentType.JSON)
            .log().all();

        responseSpec = expect()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .log().all();
    }

    @Test
    public void nobelPrizesGetTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\test\\java\\com\\team\\RoostTest\\nobelPrizes_get.csv"));

        String headerLine = reader.readLine();
        String[] headers = headerLine.split(",");

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            Map<String, String> requestParams = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                requestParams.put(headers[i], data[i]);
            }

            given()
                .spec(requestSpec)
                .queryParams(requestParams)
            .when()
                .get()
            .then()
                .spec(responseSpec)
                .body("nobelPrizes", hasSize(greaterThan(0)))
                .body("nobelPrizes[0].awardYear", instanceOf(Integer.class))
                .body("nobelPrizes[0].category.en", instanceOf(String.class))
                .body("nobelPrizes[0].category.se", instanceOf(String.class))
                .body("nobelPrizes[0].category.false", instanceOf(String.class))
                .body("nobelPrizes[0].categoryFullName.en", instanceOf(String.class))
                .body("nobelPrizes[0].dateAwarded", instanceOf(String.class))
                .body("links", hasSize(greaterThan(0)));
        }
    }

    private void validateErrorResponse(Response response) {
        response.then()
            .statusCode(400)
            .body("code", notNullValue())
            .body("message", notNullValue());
    }

    private void validateNotFoundResponse(Response response) {
        response.then()
            .statusCode(404)
            .body("code", notNullValue())
            .body("message", notNullValue());
    }

    private void validateUnprocessableEntityResponse(Response response) {
        response.then()
            .statusCode(422)
            .body("code", notNullValue())
            .body("message", notNullValue());
    }
}
