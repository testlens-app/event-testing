import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    id("java-library")
    id("org.gradle.test-retry") version "1.6.4"
}

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    targets.configureEach {
        testTask {
            testLogging {
                minGranularity = 0
                events = setOf(STANDARD_OUT, STANDARD_ERROR, FAILED)
                exceptionFormat = TestExceptionFormat.FULL
            }
            retry {
                maxRetries = providers.gradleProperty("maxRetries")
                    .map { it.toInt() }
                    .orElse(0)
            }
            ignoreFailures = providers.gradleProperty("ignoreFailures")
                .map { it.toBoolean() }
                .orElse(false)
                .get()
        }
    }
}
