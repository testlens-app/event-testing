import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins { id("java-library") }

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    targets.configureEach {
        testTask {
            testLogging {
                exceptionFormat = TestExceptionFormat.FULL
            }
        }
    }
}
