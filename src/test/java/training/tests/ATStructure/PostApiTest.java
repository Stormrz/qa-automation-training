package training.tests.ATStructure;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class PostApiTest extends BaseApiTest {

    @Test
    public void getAllPosts() {
        Response response = postClient.getAllPosts();
        response.then().statusCode(200);
    }

    @Test
    public void updatePostTitleTest() {
        Response response = postClient.updatePostTitle(1, "Updated title");
        response.then()
                .statusCode(200)
                .body("title", equalTo("Updated title"));
    }

    @Test
    public void updatePostTest() {
        Response response = postClient.updatePost(1, "Updated title", "Updated body", 1);
        response.then()
                .statusCode(200)
                .body("title", equalTo("Updated title"))
                .body("body", equalTo("Updated body"))
                .body("userId", equalTo(1));
    }

    @Test
    public void deletePostTest() {
        Response response = postClient.deletePost(1);
        response.then()
                .statusCode(200);

    }

    @Test
    public void negativeGetPostTest() {
        Response response = postClient.getPostById(9999);
        response.then()
                .statusCode(404);
    }

    @Test
    public void negativePatchPostTest() {
        Response response = postClient.updatePostTitle(9999, "New title");

        response.then()
                .statusCode(200);
    }

    @Test
    public void getPostByIdTest() {
        Response response = postClient.getPostById(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", not(emptyString()))
                .body("body", not(emptyString()))
                .body("userId", instanceOf(Integer.class))
                .body("userId", is(greaterThan(0)));
    }

    @Test
    public void negativeIdZero() {
        Response response = postClient.getPostById(0);

        response.then()
                .statusCode(400);
    }

    @Test
    public void negativeIdBelowZero() {
        Response response = postClient.getPostById(-2);

        response.then()
                .statusCode(400);
    }

    @Test
    public void negativeIdNotExist() {
        Response response = postClient.getPostById(99999);

        response.then()
                .statusCode(404);
    }

    @Test
    public void validatePostResponseTest() {
        Response response = postClient.getPostById(5);

        response.then()
                .statusCode(200)
                .body("id", 5)
                .body("userId", is(greaterThan(0)))
                .body("title", hasLength(5))
                .body("body", hasLength(10))
                .body("password", nullValue());
}
