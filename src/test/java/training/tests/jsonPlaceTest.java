package training.tests;

import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import training.tests.environment.*;
import java.util.List;
import io.restassured.common.mapper.TypeRef;
import java.util.stream.Collectors;

import java.util.List;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class jsonPlaceTest {

    private static final Logger log = LoggerFactory.getLogger(jsonPlaceTest.class);

    @Test
    public void getPost() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue())
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
    public void queryTest() {
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
        List<Integer> userIds = (response.jsonPath().getList("userId"));
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
                .body("userId", equalTo(1))
                .body("title", notNullValue());

        List<Integer> ids = response.jsonPath()
                .getList("findAll { it.status == 'SUCCESS' }.collect { it.id }");

        int count = response.jsonPath()
                .getList("findAll { it.status == 'SUCCESS' }")
                .size();

        assertThat(ids).containsExactly(1, 3);
        assertThat(count).isEqualTo(2);

        List<Integer> amount = response.jsonPath()
                .getList("findAll { it.status == 'SUCCESS' }.collect { it.amount }");
        assertThat(amount)
                .allSatisfy(value -> assertThat(value).isGreaterThan(0));
    }

    @Test
    public void headerTest() {
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1");
        response.then()
                .log().headers()
                .header("Content-Type", equalTo("application/json; charset=utf-8"));

    }

    @Test
    public void headersAndPostTest() {
        String requestBody = """
                {
                  "title": "QA test",
                  "body": "Hello API",
                  "userId": 1
                }
                """;

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .log().headers()
                .post("/posts");
        response.then()
                .statusCode(201)
                .log().body()
                .body("id", equalTo(101))
                .body("title", equalTo("QA test"))
                .body("body", equalTo("Hello API"))
                .body("userId", equalTo(1));
    }

    @Test
    public void negativeTest() {
        String requestBody = """
                {
                  "body": "Hello API",
                  "userId": 1
                }
                """;

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .log().headers()
                .post("/posts");
        response.then()
                .statusCode(400)
                .body("error", equalTo("VALIDATION_ERROR"))
                .body("message", containsString("title"));
    }

    @Test
    public void paymentTest() {
        String requestBody = """
                {
                  "amount": 0,
                  "currency": "RUB"
                }
                """;
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/payments");
        response.then()
                .statusCode(400)
                .body("error", equalTo("VALIDATION_ERROR"))
                .body("message", equalTo("amount must be greater than 0"))
                .body("message", contains("amount"))
                .body("message", contains("greater than 0"));
    }

    private final RequestSpecification requestSpec = given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer token random generator");

    private final ResponseSpecification responseSpec = expect()
            .statusCode(201)
            .contentType(ContentType.JSON);

    @Test
    public void reqTest() {
        String requestBody = """
                {
                  "body": "Hello API",
                  "userId": 1
                }
                """;

        Response response = given(requestSpec)
                .body(requestBody)
                .when()
                .post("/posts");
        response.then()
                .spec(responseSpec)
                .body("id", greaterThanOrEqualTo(1));
    }

    @Test
    public void createPostTest() {

        String requestBody = """
                {
                  "title": "QA test",
                  "body": "Hello API",
                  "userId": 1
                }
                """;

        PostClient postClient = new PostClient(requestSpec);

        Response response = postClient.createPost(requestBody);

        response.then()
                .spec(responseSpec)
                .body("id", greaterThanOrEqualTo(1));
    }

    @Test
    public void createGetUserTest() {
        UserClient userClient = new UserClient(requestSpec);

        Response response = userClient.getUser(5);

        response.then()
                .log().all()
                .statusCode(200);
    }

    @Test(description = "Создание объекта через API")
    public void paymentFromObject() {
        Payment payment = new Payment(1000, "RUB");
        PaymentClient paymentClient = new PaymentClient(requestSpec);

        Response response = paymentClient.postPayment(payment);

        response.then()
                .log().all()
                .statusCode(201)
                .body("amount", equalTo(1000))
                .body("currency", equalTo("RUB"));
        int id = response.jsonPath().getInt("id");
        assertThat(id).isEqualTo(101);
        assertThat(response.jsonPath().getInt("id"))
                .isGreaterThan(0);
    }

    @Test
    public void testGetUser() {
        UserClient userClient = new UserClient(requestSpec);
        Response response = userClient.getUser(5);
        response.then()
                .log().all()
                .statusCode(200);
        int id = response.jsonPath().getInt("id");
        assertThat(id).isEqualTo(5);
        assertThat(response.jsonPath().getString("name")).isNotBlank();
    }

    @DataProvider(name = "userIds")
    public Object[][] userIds() {
        return new Object[][]{
                {1},
                {5},
                {10}
        };
    }

    @Test(dataProvider = "userIds")
    public void dataProviderGetUserTest(int userId) {
        UserClient userClient = new UserClient(requestSpec);
        Response response = userClient.getUser(userId);
        response.then()
                .log().all()
                .statusCode(200);
        int id = response.jsonPath().getInt("id");
        assertThat(id).isEqualTo(userId);
        assertThat(response.jsonPath().getString("name")).isNotBlank();
    }

    @Test
    public void deserializationTest() {

        UserClient userClient = new UserClient(requestSpec);

        Response response = userClient.getUser(5);
        response.then().statusCode(200);

        User user = response.as(User.class);

        System.out.println("City: " + user.getAddress().getCity());
        System.out.println("Street: " + user.getAddress().getStreet());
        System.out.println("Zipcode: " + user.getAddress().getZipcode());

        assertThat(user.getId()).isEqualTo(5);
        assertThat(user.getUsername()).isNotBlank();
        assertThat(user.getEmail()).contains("@");
        assertThat(user.getName()).isNotBlank();

        assertThat(user.getAddress().getCity())
                .isEqualTo("Roscoeview");
        assertThat(user.getAddress().getStreet())
                .isEqualTo("Skiles Walks");

        System.out.println("ID: " + user.getId());
        System.out.println("Name: " + user.getName());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());

    }

    @Test
    public void getUsersTestList() {
        Response response = given(requestSpec)
                .when()
                .get("/users");

        response.then().statusCode(200);

        List<User> users = response.as(
                new TypeRef<List<User>>() {}
        );
        System.out.println("Количество пользователей: " + users.size());

        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("----------------");
        }
        for (User user : users) {
            System.out.println("Email:" + user.getEmail());
        }
    }

    @Test
    public void lambdaTest() {
        Response response = given(requestSpec)
                .when()
                .get("/users");

        response.then().statusCode(200);

        List<User> users = response.as(
                new TypeRef<List<User>>() {}
        );
        users.stream()
                .filter(user -> user.getId() < 5)
                .toList();
    }

    @Test
    public void mapTest() {
        Response response = given(requestSpec)
                .when()
                .get("/users");

        response.then().statusCode(200);

        List<User> users = response.as(
                new TypeRef<List<User>>() {}
        );

        List<String> emails = users.stream()
                .map(user -> user.getEmail())
                .toList();

        List<Integer> ids = users.stream()
                .filter(user -> user.getId() > 5)
                .map(user -> user.getId())
                .toList();

        List<String> names = users.stream()
                .filter(user -> user.getId() > 5)
                .map(User::getName)
                .toList();

                List<String> sortedEmails = users.stream()
                        .map(User::getEmail)
                        .sorted()
                        .toList();

        List<String> distinctEmails = users.stream()
                .map(User::getEmail)
                .distinct()
                .toList();
        System.out.println("Уникальные email: " + distinctEmails);

        long count = users.stream()
                .filter(user -> user.getEmail().endsWith(".com"))
                .count();
        System.out.println("count " + count);

        boolean matcher = users.stream()
                .anyMatch(user -> user.getId() > 5)

    }

    }
