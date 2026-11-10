package training.tests.environment;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Payment {
    private final int amount;

    private final String currency;

    public Payment (int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
    return currency;
    }


}
