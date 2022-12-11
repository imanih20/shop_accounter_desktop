import org.gradle.internal.component.model.Exclude
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.mohyeddin"
version = "1.0-SNAPSHOT"

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    val exposedVersion: String by project
    val koinVersion: String by project
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("br.com.devsrsouza.compose.icons.jetbrains:font-awesome:1.0.0")
                implementation("ir.huri:JalaliCalendar:1.3.3")
                implementation("org.xerial:sqlite-jdbc:3.30.1")
                implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("org.slf4j:slf4j-nop:2.0.5")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
            packageName = "Accounter"
            packageVersion = "1.0.0"
        }
    }
}
