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
}

dependencies {
    implementation(compose.desktop.currentOs)
    testImplementation(kotlin("test"))
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
