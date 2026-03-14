plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.github.kauanjpk"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")

    maven {
        url = uri("https://repo1.maven.org/maven2/")
    }
}
dependencies {

    // API do Spigot para compilar
    compileOnly(files("libs/spigot-1.8.8.jar"))

    // Kotlin runtime
    implementation(kotlin("stdlib"))


    // JSON

    // JWT
    implementation("com.auth0:java-jwt:4.4.0")

    // Coroutines
    implementation("org.nanohttpd:nanohttpd:2.3.1")
    implementation("net.dv8tion:JDA:5.0.0-beta.12")

}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

kotlin {
    jvmToolchain(8)
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.shadowJar {

    archiveBaseName.set("NaruCore")
    archiveVersion.set("SNAPSHOT_1.0")
    archiveClassifier.set("")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    mergeServiceFiles()

    exclude("META-INF/*.SF")
    exclude("META-INF/*.DSA")
    exclude("META-INF/*.RSA")
}
tasks {
    build {
        dependsOn(shadowJar)
    }
}
tasks.build {
    dependsOn(tasks.shadowJar)
}