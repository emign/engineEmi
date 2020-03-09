buildscript {
    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        // maven { url = uri("https://dl.bintray.com/korlibs/korlibs") } // REMOVE
        mavenCentral()

    }
    dependencies {
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
        classpath("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4")
        //  classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:1.5.6.2") // REMOVE
    }
}

plugins {
    kotlin("multiplatform") version "1.3.70"
    id("maven-publish")
    id("org.jetbrains.dokka") version "0.10.1"
}


repositories {
    jcenter()
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/korlibs/korlibs")
    }
}

val sourceSets: SourceSetContainer by project
val publishing: PublishingExtension by project



tasks {
    val dokka by getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }
}

kotlin {
    jvm {
    }
    js {
        browser {
        }
    }

    macosX64()
    iosArm32()
    iosArm64()
    iosX64()
    mingwX64()
    linuxX64()


    // For ARM, should be changed to iosArm32 or iosArm64
    // For Linux, should be changed to e.g. linuxX64
    // For MacOS, should be changed to e.g. macosX64
    // For Windows, should be changed to e.g. mingwX64

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api("com.soywiz.korlibs.korge:korge:${project.property("korgeVersion")}")
                api("com.soywiz.korlibs.korge:korge-box2d:${project.property("korgeVersion")}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }

            jvm().compilations["test"].defaultSourceSet {
                dependencies {
                    implementation(kotlin("test-junit"))
                }
            }

            js().compilations["main"].defaultSourceSet {
                dependencies {
                    implementation(kotlin("stdlib-js"))
                }
            }

            js().compilations["test"].defaultSourceSet {
                dependencies {
                    implementation(kotlin("test-js"))
                }
            }
        }
    }
}


publishing.apply {
    repositories {
        maven {
            credentials {
                username = "emign"
                password = System.getenv("bintrayApiKey")
            }
            url = uri(
                "https://api.bintray.com/maven/${project.property("BINTRAY_ORGANIZATION")}/${project.property("BINTRAY_REPOSITORY")}/${project.property(
                    "ARTIFACT_ID"
                )}/"
            )
        }
    }
    publications {
        maybeCreate<MavenPublication>("maven").apply {
            groupId = "${project.property("GROUP_ID")}"
            artifactId = "${project.property("ARTIFACT_ID")}"
            version = "${project.property("version")}"
            from(components["java"])
        }
    }
}

