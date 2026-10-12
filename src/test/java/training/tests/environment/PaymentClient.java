package training.tests.environment;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PaymentClient {

    private final RequestSpecification requestSpec;

    public PaymentClient (RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Response getPayment(int id) {
        return given(requestSpec)
                .pathParam("id", id)
                .when()
                .get("/payments/{id}");
    }

    public Response postPayment(Payment payment) {

        return given(requestSpec)
                .body(payment)
                .when()
                .post("/posts");
    }



}
