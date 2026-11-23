package training.tests.ATStructure;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    protected UserClient userClient;
    protected PostClient postClient;

    @BeforeClass
    public void setUp() {
        RequestSpecification requestSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com");

        userClient = new UserClient(requestSpec);
        postClient = new PostClient(requestSpec);
    }
}