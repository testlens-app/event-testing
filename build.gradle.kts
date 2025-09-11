import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins { id("java-library") }

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    targets.configureEach {
        testTask {
            testLogging {
                minGranularity = 0
                events = setOf(STANDARD_OUT, STANDARD_ERROR, FAILED)
                exceptionFormat = TestExceptionFormat.FULL
            }
        }
    }
}
