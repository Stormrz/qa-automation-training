package training.tests.environment;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Payment {
    private final int amount;

    private final String currency;

    public Payment payment (amount, currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public int getCurrency() {
    return currency;
    }

    public Response postPayment(int amount, String currency) {

        String requestBody = """
            {
                "amount": %d,
                "currency": "%s"
            }
            """.formatted(amount, currency);

        return given()
                .body(requestBody)
                .when()
                .post("/payments");
    }
}
