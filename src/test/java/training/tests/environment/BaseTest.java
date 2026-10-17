
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeMethod
    public void setUp() {
        requestSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON);
    }
}