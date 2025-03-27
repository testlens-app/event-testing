import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins { id("java-library") }

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    targets {
        testTasks.configureEach {
            testLogging {
                exceptionFormat = TestExceptionFormat.FULL
            }
        }
    }
}
