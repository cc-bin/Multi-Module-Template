@file:OptIn(ExperimentalSpmForKmpFeature::class)

import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import io.github.frankois944.spmForKmp.definition.product.ProductName
import io.github.frankois944.spmForKmp.swiftPackageConfig
import io.github.frankois944.spmForKmp.utils.ExperimentalSpmForKmpFeature
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
//    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kmpApplication)
}

kotlin {

    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {
            implementation(projects.core)
            implementation(libs.store)
        }
        commonTest.dependencies {

        }
        desktopMain.dependencies {

        }
        iosMain.dependencies {

        }
    }
}

android {
    namespace = "com.example.multi_module_template"

    defaultConfig {
        applicationId = "com.example.multi_module_template"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            configure<CrashlyticsExtension> {
                // Enable processing and uploading of native symbols to Firebase servers.
                // By default, this is disabled to improve build speeds.
                // This flag must be enabled to see properly-symbolicated native
                // stack traces in the Crashlytics dashboard.
                nativeSymbolUploadEnabled = true
            }
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.example.multi_module_template.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.multi_module_template"
            packageVersion = "1.0.0"
        }
    }
}

compose.resources {
    publicResClass = true
    generateResClass = always
    packageOfResClass = "composeapp.generated.resources"
}