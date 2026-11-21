package training.tests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.AbstractIterableAssert;
import org.testng.annotations.Test;
import training.tests.environment.User;
import training.tests.environment.UserClient;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserClientPracticeTest {
    @Test
    public void getAllClients() {
        RequestSpecification requestSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com");
        UserClient userClient = new UserClient(requestSpec);
        Response response = userClient.getAllUsers();
        List<User> users = response.as(new TypeRef<List<User>>() {});
        Response response = getAllClients();
        response.then().statusCode(200);
    }
}
