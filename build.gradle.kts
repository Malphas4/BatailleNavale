plugins {
    id("java")
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "M1.reseau"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

/*javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}*/

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}