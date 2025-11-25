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
        register("composeMultiplatformPlugin") {
            id = "org.convention.compose.multiplatform"
            implementationClass = "ComposeMultiplatformPlugin"
        }
    }
}
