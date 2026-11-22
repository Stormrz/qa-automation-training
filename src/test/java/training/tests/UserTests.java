package training.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import training.tests.ATStructure.BaseApiTest;

public class UserTests extends BaseApiTest {

    @Test
    public void getAllUsersTest() {
        Response response = userClient.getAllUsers();

        response.then().statusCode(200);
    }
}