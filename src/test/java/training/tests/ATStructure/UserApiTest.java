package training.tests.ATStructure;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserApiTest extends BaseApiTest {

    @Test
    public void getAllUsersTest() {
        Response response = userClient.getAllUsers();

        response.then().statusCode(200);
    }
}
