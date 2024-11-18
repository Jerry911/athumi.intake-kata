import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.21"
    jacoco
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)    // Generate XML report for CI integration
        csv.required.set(false)  // Disable CSV if not needed
        html.required.set(true)  // Enable HTML for human-readable reports
    }
}

// Add jacocoTestReport to the build lifecycle
tasks.build {
    dependsOn(tasks.jacocoTestReport)
}

dependencies {
    val junitVersion = "3.26.3"
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.assertj:assertj-core:$junitVersion")

    testCompileOnly("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:$junitVersion")
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}
