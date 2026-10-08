package training.tests.environment;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserClient {
    private final RequestSpecification requestSpec;

    public UserClient (RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response getUser (int id) {
        return given(requestSpec)
                .pathParam("id", id)
                .when()
                .get("/users/{id}");
    }

    public Response postPayment(int amount, String currency) {

        String requestBody = """
            {
                "amount": %d,
                "currency": "%s"
            }
            """.formatted(amount, currency);

        return given(requestSpec)
                .body(requestBody)
                .when()
                .post("/payments");
    }
}
