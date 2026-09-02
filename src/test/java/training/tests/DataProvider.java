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
    }1

    @Test

}
