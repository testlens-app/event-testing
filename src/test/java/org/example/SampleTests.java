package org.example;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.nio.file.Files;
import java.nio.file.Path;

abstract class SampleTests {

    private static boolean firstRunFinished = false;

    private static Path markerFile;
    private static boolean markerFileDoesNotExist;

    @BeforeAll
    static void checkForMarkerFile(TestInfo testInfo) {
        markerFile = Path.of("build/marker-%s.txt".formatted(testInfo.getTestClass().orElseThrow().getSimpleName()));
        markerFileDoesNotExist = Files.notExists(markerFile);
    }

    @AfterAll
    static void createMarkerFile() throws Exception {
        if (markerFileDoesNotExist) {
            Files.createFile(markerFile);
        }
    }

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
        maybeFail();
    }

    @Test
    void test4(TestReporter reporter) {
        System.out.println("it works");
        reporter.publishEntry("foo", "bar");
    }

    @Test
    void test5_failing() {
        System.out.println("it fails");
        maybeFail();
    }

    private static void maybeFail() {
        if (markerFileDoesNotExist) {
            fail();
        }
    }
}
