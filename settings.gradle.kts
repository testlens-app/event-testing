dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("local-repo") {
            metadataSources {
                artifact()
            }
        }
    }
}
