import org.testng.annotations.Test;

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
    }
}