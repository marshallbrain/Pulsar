plugins {
    kotlin("jvm") version "1.6.0-RC2"
}

group = "com.marshalldbrain"
version = "0.0.1"

val kotestVersion = "5.0.0.M3"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}