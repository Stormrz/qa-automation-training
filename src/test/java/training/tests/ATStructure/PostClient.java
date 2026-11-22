package training.tests.ATStructure;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostClient extends BaseApiTest {
    private final RequestSpecification requestSpec;

    public PostClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response getAllPosts() {
        return given(requestSpec)
                .when()
                .get("/posts");
    }
}
