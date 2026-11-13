package training.tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WildcardsPracticeTest {
    public void printNumbers(List<? extends Number> numbers) {
            for (Number number : numbers) {
                System.out.println(number);
            }
    }

    @Test
    public void wildcardsTest() {
        List<Integer> integers = List.of(10, 20, 30);
        List<Double> doubles = List.of(1.5, 2.5, 3.5);

        printNumbers(integers);
        printNumbers(doubles);

        List<Number> numbers = new ArrayList<>();
        addNumber(numbers);

        System.out.println(numbers);
    }

    public void addNumber(List<? super Number> numbers) {
        numbers.add(100);
        numbers.add(99.5);
    }
}
