import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("org.jetbrains.intellij") version "1.3.1"
    kotlin("jvm") version "1.6.20-M1"
    java
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "1.3.1"
}

group = "com.nekofar.milad"
version = "1.0.0-alpha.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    version.set(properties("pluginVersion"))
    keepUnreleasedSection.set(true)
    unreleasedTerm.set("[unreleased]")
    groups.set(emptyList())
}

tasks {
    // Set the JVM compatibility versions
    properties("javaVersion").let {
        withType<JavaCompile> {
            sourceCompatibility = it
            targetCompatibility = it
        }
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = it
        }
    }

    wrapper {
        gradleVersion = properties("gradleVersion")
    }

    patchPluginXml {
        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            changelog.run {
                getOrNull(properties("pluginVersion")) ?: getLatest()
            }.toHTML()
        })
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}