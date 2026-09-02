package training.tests;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        int total = calculateAmount(100,3);
        assertThat(total).isEqualTo(300);
    }

    int amount = 100;
    public boolean isValidPayment(int amount);
    if (amount > 0) {
        return true;
    } else {
        return false;
    }

}