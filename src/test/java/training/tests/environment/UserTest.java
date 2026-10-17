package training.tests.environment;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    @Test
    public void testGetUser() {
        UserClient userClient = new UserClient(requestSpec);

        Response response = userClient.getUser(5);

        response.then().statusCode(200);
    }
}