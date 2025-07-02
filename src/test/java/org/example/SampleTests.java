package org.example;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

@Disabled
public class SampleTests {

    private static boolean firstRunFinished = false;

    @Test
    void test1() {
        System.out.println("it works");
    }

    @Test
    void test2() {
        System.out.println("it works");
    }

    @Test
    void test3_flaky() throws InterruptedException {
        Thread.sleep(2600);
        if (firstRunFinished) {
            return;
        }

        firstRunFinished = true;
        fail();
    }

    @Test
    void test4(TestReporter reporter) {
        System.out.println("it works");
        reporter.publishEntry("foo", "bar");
    }

    @Test
    void test5_failing() {
        System.out.println("it fails");
        fail();
    }
}
