val neo4jDriverVersion: String by project

plugins {
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0-Beta1"
    id("org.jetbrains.compose") version "1.8.0-beta02"
    application
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://raw.github.com/gephi/gephi/mvn-thirdparty-repo/")
}

dependencies {
    implementation(compose.desktop.currentOs)
    testImplementation(kotlin("test"))
    implementation("org.neo4j.driver", "neo4j-java-driver", neo4jDriverVersion)
    implementation("org.jetbrains.exposed:exposed-core:0.45.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")
    implementation("io.github.microutils", "kotlin-logging-jvm", "2.0.6")
    implementation("org.slf4j", "slf4j-simple", "1.7.29")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.gephi", "gephi-toolkit", "0.10.1", classifier = "all")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

application { mainClass = "org.graphApp.AppKt" }

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.11.3")
        }
    }
}
