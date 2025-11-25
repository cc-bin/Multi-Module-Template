plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatformPlugin)
}

android {
    namespace = "org.core"
}

kotlin {

    sourceSets {
        androidMain.dependencies {
            api(compose.preview)
            api(libs.androidx.activity.compose)
            api(libs.kotlinx.coroutines.android)
        }
        commonMain.dependencies {
            api(libs.multiplatform.settings.no.arg)
            api(libs.multiplatform.settings.coroutines)
            api(libs.multiplatform.settings.serialization)

            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(libs.androidx.lifecycle.viewmodelCompose)
            api(libs.androidx.lifecycle.runtimeCompose)

            api(libs.kotlinx.coroutines.core)

        }
        commonTest.dependencies {
            api(libs.kotlin.test)
        }
        jvmMain.dependencies {
            api(compose.desktop.currentOs)
            api(libs.kotlinx.coroutinesSwing)
        }
        iosMain.dependencies {

        }
    }
}