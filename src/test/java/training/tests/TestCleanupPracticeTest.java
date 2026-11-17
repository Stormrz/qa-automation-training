package training.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestCleanupPracticeTest {

    private int userId;
    private int createStatusCode;

    @BeforeMethod
    public void setUp() {
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType("application/json")
                .body("""
                    {
                        "name": "Test User",
                        "username": "testuser",
                        "email": "test@example.com"
                    }
                    """)
                .when()
                .post("/users");

        userId = response.jsonPath().getInt("id");
        createStatusCode = response.statusCode();

        System.out.println("Created user ID: " + userId);
    }

    @Test
    public void checkCreatedUserTest() {
        System.out.println("Testing user ID: " + userId);

        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users/1")
                .then()
                .statusCode(200);
        Assert.assertEquals(createStatusCode, 201);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Cleanup user ID: " + userId);

        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(200);
    }
}