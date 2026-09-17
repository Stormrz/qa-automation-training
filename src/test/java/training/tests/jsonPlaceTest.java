package training.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class jsonPlaceTest {

    @Test
    public void getPost() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("userId",equalTo(1))
                .body("title",notNullValue())
                .log().body();
    }

    @Test
    public void createPost() {
        String requestBody = """
{
  "title": "QA test",
  "body": "Hello API",
  "userId": 1
}
""";
        int postId = 1;
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .body(requestBody)
                .log().all()
                .when()
                .post("/posts");
                response.then()
                .statusCode(201);
        postId = response.jsonPath().getInt("id");
        System.out.println(postId);
        assertThat(postId).isGreaterThan(0);

        System.out.println("Get");
        Response responseGet = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .pathParams("id", postId)
                .log().all()
                .when()
                .get("/posts/{id}");
        responseGet.then()
                .log().all()
                .statusCode(200);
        int getId = responseGet.jsonPath().getInt("id");
        assertThat(getId).isEqualTo(postId);
        System.out.println("getID" + getId);
    }
    @Test
    public void queryTest () {
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .queryParam("userId", 1)
                .queryParam("_limit", 3)
                .log().all()
                .when()
                .get("/posts");
                response.then()
                        .log().all()
                .statusCode(200);
                int countArray = response.jsonPath().getList("$").size();
                assertThat(countArray).isEqualTo(3);
        List <Integer> userIds = (response.jsonPath().getList("userId"));
        assertThat(userIds).containsOnly(1);
        int count = response.jsonPath().getList("findAll {it.userId == 1 }").size();
        System.out.println(count);
    }

    @Test
    public void newTest() {
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1");
        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId",equalTo(1))
                .body("title", notNullValue())
                .body()
    }


}
