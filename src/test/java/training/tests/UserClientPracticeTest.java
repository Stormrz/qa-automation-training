package training.tests;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import training.tests.environment.User;
import training.tests.ATStructure.UserClient;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserClientPracticeTest {
    @Test
    public void getAllClients() {
        RequestSpecification requestSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com");
        UserClient userClient = new UserClient(requestSpec);
        Response response = userClient.getAllUsers();
        List<User> users = response.as(new TypeRef<List<User>>() {});
        response.then().statusCode(200);
        assertThat(users).isNotNull();
        assertThat(users).hasSize(10);

        User First = users.get(0);
        assertThat(First.getId()).isEqualTo(1);
        assertThat(First.getName()).isEqualTo( "Leanne Graham");
        assertThat(First.getUsername()).isEqualTo("Bret");
    }
}
