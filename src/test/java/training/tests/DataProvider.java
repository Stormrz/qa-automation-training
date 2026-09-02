package training.tests;

import org.testng.annotations.Test;

public class DataProvider {
    @DataProvider(name = "payments")
    public Object[][] payments() {
        return new Object[][] {
                {1000, "RUB", true},
                {1000, "USD", false},
                {0, "RUB", false},
                {1, "RUB", true},
                {-100, "RUB", false}
        }
    }

    @Test(dataProvider = "payments")
    public void checkValidPayment(int amount, String currency, boolean expected) {
    }

}
