package training.tests.ATStructure;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

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

    public Response updatePostTitle(int id, String title) {
        Map<String, Object> body = Map.of(
                "title", title
        );
        return given(requestSpec)
                .pathParam("id", id)
                .body(body)
            .when()
                .patch("/posts/{id}");
    }

    public Response updatePost(int id, String title, String bodyText, int userId) {
        Map<String, Object> requestBody = Map.of(
                "title", title,
                "body", bodyText,
                "userId", userId
        );
        return given(requestSpec)
                .pathParam("id", id)
                .body(requestBody)
                .when()
                .put("/posts/{id}");
    }

    public Response deletePost(int id) {
        return given(requestSpec)
                .pathParam("id", id)
                .when()
                .delete("/posts/{id}");
    }

    public Response getPostById(int id) {
        return given(requestSpec)
                .pathParam("id", id)
                .when()
                .get("/posts/{id}");
   }
}
