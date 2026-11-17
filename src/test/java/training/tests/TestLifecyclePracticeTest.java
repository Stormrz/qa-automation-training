package training.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLifecyclePracticeTest {

    @BeforeMethod
    public void setUp() {
        System.out.println("BeforeMethod");
    }

    @Test
    public void firstTest() {
        System.out.println("First test");
    }

    @Test
    public void secondTest() {
        System.out.println("Second test");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("AfterMethod");
    }
}