plugins {
    kotlin("jvm") version "2.1.0"
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    dependencies {
        testImplementation(kotlin("test"))
        implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
        implementation("org.slf4j:slf4j-simple:2.0.16")
        //For Streaming to XML and JSON
        implementation("com.thoughtworks.xstream:xstream:1.4.18")
        implementation("org.codehaus.jettison:jettison:1.4.1")

    }

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}