import java.io.PrintWriter
import java.net.URL
import java.util.*

buildscript {
    val korgePluginVersion: String by project
    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://dl.bintray.com/korlibs/korlibs") } // REMOVE
        mavenCentral()

    }
    dependencies {
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
        classpath("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4")
        classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
    }
}


plugins {
    kotlin("multiplatform") version "1.3.72"
    id("maven-publish")
    id("maven")
    id("org.jetbrains.dokka") version "0.10.1"
    id("java")
    // id("com.moowork.node") version "1.2.0"
}

//apply(plugin = "maven")
//apply(plugin = "maven-publish")

apply<com.soywiz.korge.gradle.KorgeGradlePlugin>()



repositories {
    jcenter()
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://dl.bintray.com/korlibs/korlibs")
    }
}


val GROUP_ID: String by project
val ARTIFACT_ID: String by project
val BINTRAY_ORGANIZATION: String by project
val BINTRAY_REPOSITORY: String by project
val engineVersion: String by project
val korgeVersion: String by project
val kotlinVersion: String by project

group = GROUP_ID
version = engineVersion

println("Group: $group, Version $version")

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
                api("com.soywiz.korlibs.korge:korge:${korgeVersion}")
                api("com.soywiz.korlibs.korge:korge-box2d:${korgeVersion}")
                //  api("com.moowork.gradle:gradle-node-plugin:1.2.0")

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
val sourceSets: SourceSetContainer by project
val publishing: PublishingExtension by project

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        val kotlinMultiplatform by getting {
            repositories {
                maven {
                    credentials {
                        username = "emign"
                        password = System.getenv("bintrayApiKey")

                    }
                    url = uri(
                        "https://api.bintray.com/maven/emign/engineEmi/engineEmi/"

                    )
                }
            }
        }
    }
}


/*
publishing {
    publications {
        val kotlinMultiplatform by getting {
            create<MavenPublication>("maven").apply {


                groupId = GROUP_ID
                artifactId = ARTIFACT_ID
                version = engineVersion


                from(components["java"])
                artifact(sourcesJar)
                artifact(javadocJar)


                pom {
                    name.set("engineEmi")
                    description.set("Desc")
                    url.set("https.:emig.me")
                    url.set("https://github.com/emign/engineEmi")
                    scm {
                        url.set("https://github.com/emign/engineEmi")
                    }
                }

                repositories {
                    maven {
                        credentials {
                            username = "emign"
                            password = System.getenv("bintrayApiKey")

                        }
                        url = uri(
                            "https://api.bintray.com/maven/emign/engineEmi/engineEmi/"

                        )
                    }
                }
            }
        }
    }
}
*/
/*

publishing.apply {
    repositories {
        maven {
            credentials {
                username = "emign"
                password = System.getenv("bintrayApiKey")

            }
            url = uri(
                "https://api.bintray.com/maven/emign/engineEmi/engineEmi/"

            )
        }
    }



    publications {
        create<MavenPublication>("maven").apply {


            groupId = GROUP_ID
             artifactId = ARTIFACT_ID
             version = engineVersion


            from(components["java"])
             artifact(sourcesJar)
             artifact(javadocJar)


            pom {
                name.set("engineEmi")
                description.set("Desc")
                url.set("https.:emig.me")
                url.set("https://github.com/emign/engineEmi")
                scm {
                    url.set("https://github.com/emign/engineEmi")
                }
            }
        }
    }
}

     */
fun ByteArray.encodeBase64() = Base64.getEncoder().encodeToString(this)

val release by tasks.creating {
   // dependsOn("publish")
    group = "publishing"

    doLast {
        val subject = BINTRAY_ORGANIZATION
        val repo = "engineEmi"
        val _package = ARTIFACT_ID
        val version = engineVersion

        ((URL("https://bintray.com/api/v1/content/$BINTRAY_ORGANIZATION/$BINTRAY_REPOSITORY/$BINTRAY_REPOSITORY/$version/publish")).openConnection() as java.net.HttpURLConnection).apply {

            requestMethod = "POST"
            doOutput = true


            //setRequestProperty("Authorization", "Basic " + "$publishUser:$publishPassword".toByteArray().encodeBase64().toString())
            setRequestProperty(
                "Authorization",
                "Basic " + "emign:${System.getenv("bintrayApiKey")}".toByteArray().encodeBase64().toString()

            )
            PrintWriter(outputStream).use { printWriter ->
                printWriter.write("""{"discard": false, "publish_wait_for_secs": -1}""")
            }
            println(inputStream.readBytes().toString(Charsets.UTF_8))
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }