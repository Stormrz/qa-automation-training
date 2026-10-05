package training.tests.environment;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostClient {

    private final RequestSpecification requestSpec;

    public PostClient(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response createPost(String requestBody) {

        return given(requestSpec)
                .body(requestBody)
                .when()
                .post("/posts");
    }
}