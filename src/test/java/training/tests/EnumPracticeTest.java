package training.tests;
import org.testng.annotations.Test;
import training.tests.environment.PaymentStatus;

public class EnumPracticeTest {

    @Test
    public void enumTest() {

        PaymentStatus status = PaymentStatus.CREATED;
        PaymentStatus statusComplete = PaymentStatus.COMPLETED;

        System.out.println(status);
        System.out.println(status.name());
        System.out.println(statusComplete);
        System.out.println(statusComplete.name());

        boolean statusCheck = statusComplete == PaymentStatus.FAILED;
        System.out.println(statusCheck);

        for (PaymentStatus value : PaymentStatus.values()) {
            System.out.println(value);
        }

        PaymentStatus statusOf = PaymentStatus.valueOf("COMPLETED");
        System.out.println(statusOf);
        System.out.println(PaymentStatus.CREATED.ordinal());
        PaymentStatus statusOfFailed = PaymentStatus.valueOf("Failed");
        System.out.println(statusOfFailed);
        System.out.println(PaymentStatus.FAILED.ordinal());
        System.out.println(PaymentStatus.valueOf("FAILED"));
    }
}