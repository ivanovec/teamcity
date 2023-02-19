import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junitVersion = "5.7.0"
val jacksonVersion = "2.10.4"
val typesafeVersion = "1.4.1"
val allureIntegrationsVersion = "2.21.0"
val restAssuredVersion = "5.3.0"
val junitLauncherVersion = "1.7.0"
val assertjVersion = "3.19.0"
val selenideVersion = "6.11.2"

plugins {
    kotlin("jvm") version "1.7.21"
    id("io.qameta.allure") version "2.11.2"
}

group = "simple.automation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.qameta.allure:allure-rest-assured:$allureIntegrationsVersion")
    testImplementation("io.qameta.allure:allure-assertj:$allureIntegrationsVersion")
    testImplementation("io.qameta.allure:allure-selenide:$allureIntegrationsVersion")


    testImplementation("org.junit.platform:junit-platform-launcher:$junitLauncherVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("com.codeborne:selenide:$selenideVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test>{
    useJUnitPlatform()
    outputs.upToDateWhen {false}
}

tasks.register<Test>("uitests").configure{
    filter{
        includeTestsMatching("ui.tests.*")
    }
}

tasks.register<Test>("resttests").configure{
    filter{
        includeTestsMatching("rest.tests.*")
    }
}

allure{
    adapter{
        autoconfigure
        aspectjWeaver
    }
}