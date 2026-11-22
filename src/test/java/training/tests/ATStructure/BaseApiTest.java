package training.tests.ATStructure;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    protected UserClient userClient;

    @BeforeClass
    public void setUp() {
        RequestSpecification requestSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com");

        userClient = new UserClient(requestSpec);
    }
}