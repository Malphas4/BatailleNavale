plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "M1.reseau"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "17"
    //possible solution
    //sdk = "/Library/Java/Extensions/javafx-sdk-17.0.9" // Path to your javafx sdk
    modules("javafx.controls", "javafx.fxml") //"javafx.base", "javafx.graphics"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "M1.reseau.Main"
}