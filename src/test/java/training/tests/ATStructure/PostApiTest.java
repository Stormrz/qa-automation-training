package training.tests.ATStructure;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

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
}
