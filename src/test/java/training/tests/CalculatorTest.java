package training.tests;

import org.testng.annotations.Test;

public class CalculatorTest {
    public void sum(){
        int a = 5;
        int b = 10;
        int sum = a + b;
        System.out.println(Integer.toString(sum));
    };

    @Test
    public class testSum {
    };
}
