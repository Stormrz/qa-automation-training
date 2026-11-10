import org.testng.annotations.Test;
import training.tests.environment.Box;
import training.tests.environment.NumberBox;

import java.util.List;

public class GenericsPracticeTest {

    @Test
    public void genericsTest() {

        List<String> names = List.of(
                "Alex",
                "Ivan",
                "Peter"
        );

        List<Integer> numbers = List.of(
                10,
                20,
                30
        );

        System.out.println("Names: " + names);
        System.out.println("Numbers: " + numbers);

        List<String> currencies = List.of(
                "RUB",
                "USD",
                "EUR"
        );

        List<Integer> amounts = List.of(
                1000,
                2500,
                5000
        );

        List<Boolean> paymentStatuses = List.of(
                true,
                false,
                true
        );

        System.out.println("currencies: " + currencies);
        System.out.println("amounts: " + amounts);
        System.out.println("paymentStatuses: " + paymentStatuses);
    }

    @Test
    public void boxTest() {

        Box<String> currency = new Box<>("RUB");
        Box<Integer> amount = new Box<>(1000);

        System.out.println(currency.getValue());
        System.out.println(amount.getValue());
    }

    @Test
    public void boxNumberTest() {

        NumberBox<Integer> currency = new NumberBox<>(100);
        NumberBox<Double> amount = new NumberBox<>(99.5);

        System.out.println(currency.getValue());
        System.out.println(amount.getValue());

        NumberBox<Integer> doubleValue = new NumberBox<>(100);
        System.out.println(doubleValue.getDoubledValue());
    }
}