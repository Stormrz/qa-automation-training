package training.tests;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.DataProvider;

public class FirstTest {
    @Test
    public void printHello() {
        System.out.println("Hello QA");
    }

    public int calculateAmount(int price, int quantity) {
        int amount = price * quantity;
        return amount;
    }

    @Test
    public void checkAmount() {
        int amount = 1000;

        assertThat(amount).isEqualTo(1000);
        assertThat(amount).isGreaterThan(0);
    }

    @Test
    public void checkPayment() {
        int amount = 1000;
        String currency = "RUB";

        assertThat(amount).isEqualTo(1000);
        assertThat(amount).isGreaterThan(0);
        assertThat(currency).isEqualTo("RUB");
    }

    @Test
    public void checkCalculateAmount() {
        int total = calculateAmount(100, 3);
        assertThat(total).isEqualTo(300);
    }


    public boolean isValidPayment(int amount) {
        if (amount > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void checkValidPayment1() {
        assertThat(isValidPayment(1000)).isEqualTo(true);
        assertThat(isValidPayment(0)).isEqualTo(false);
    }

    public boolean isValidPayment(int amount, String currency) {
        if (amount > 0 && currency.equals("RUB")) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void checkValidPayment() {
        assertThat(isValidPayment(1000, "RUB")).isTrue();
        assertThat(isValidPayment(1000, "USD")).isFalse();
        assertThat(isValidPayment(0, "RUB")).isFalse();
    }

        @DataProvider (name ="payments")

        public Object[][] payments() {
            return new Object[][]{
                    {1000, "RUB", true},
                    {1000, "USD", false},
                    {0, "RUB", false},
                    {1, "RUB", true},
                    {-100, "RUB", false}
            };
        }

        @Test(dataProvider = "payments")
        public void checkValidPayment(int amount, String currency, boolean expected) {
            assertThat(isValidPayment(amount, currency)).isEqualTo(expected);
        }2
    }
