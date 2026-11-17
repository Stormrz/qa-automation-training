package training.tests;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestSpecificationLifecycleTest {

    private RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        requestSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Accept", "application/json");

        System.out.println("Request specification initialized");
    }

    @Test
    public void getFirstUserTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void getSecondUserTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("id", equalTo(2));
    }
}