package training.tests;

import org.testng.annotations.Test;

public class ExceptionsPracticeTest {
    @Test
    public void exceptionTest() {

        try {
            int number = 10;
            int result = number / 0;

            System.out.println(result);

        } catch (ArithmeticException e) {
            System.out.println("Ошибка математической операции");

        } catch (NullPointerException e) {
            System.out.println("Обращение к null");

        } catch (Exception e) {
            System.out.println("Другая ошибка");
        }

        System.out.println("Тест завершён");
    }

    @Test
    public void exceptionTestNull() {
        try {
            String number = null;
            //number.length();

        } catch (NullPointerException e) {
            System.out.println("Поймали NullPointerException");

        } finally {
            System.out.println("Блок finally выполнен");
        }

        System.out.println("Тест завершён");
    }

    public void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException ("Ошибка суммы");
        }
    }

    @Test
    public void validateAmountTest() {
        validateAmount(-100);
    }

    public void validateAmountThrows(int amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
    }

    @Test
    public void validateAmountCatchTest() {
        try {
            validateAmountThrows(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймали ошибку: " + e.getMessage());
        }

        System.out.println("Проверка завершена");
    }

    validatePayment(int amount, String currency) {
1
    }

}
