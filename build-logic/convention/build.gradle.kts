import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

dependencies{
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
}

gradlePlugin {
    plugins {
        // Android Plugins
        register("kmpLibrary") {
            id = "org.convention.kmp.library"
            implementationClass = "KMPLibraryPlugin"
        }

        register("kmpApplication") {
            id = "org.convention.kmp.application"
            implementationClass = "KMPApplicationPlugin"
        }
    }
}
