package training.tests.ATStructure;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserInheritancePracticeTest extends BaseApiTest {

    @Test
    public void getAllUsersTest() {
        Response response = userClient.getAllUsers();

        response.then().statusCode(200);
    }

    @Test
    public void getAllPosts() {
        Response response = postClient.getAllPosts();
        response.then().statusCode(200);
    }
}