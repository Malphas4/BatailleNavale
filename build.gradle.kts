plugins {
    id("java")
    //id ("application")
   // id ("org.openjfx.javafxplugin") version "0.0.9"
   // id (org.beryx.jlink) version "2.12.0"
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


