plugins {
    alias(libs.plugins.kmpLibrary)
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
            implementation(libs.koin.android)

            implementation(libs.firebase.bom)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics.ndk)
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

            api(libs.koin.ktor)
            api(libs.koin.core)
            api(libs.koin.test)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)

            implementation(libs.slf4j.simple)
            api(libs.kotlin.logging)

            implementation(libs.store)

            api(libs.coil.compose)
            implementation(libs.coil.network.ktor3)

            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.flow)
            api(libs.ktor.client.logging)
        }
        commonTest.dependencies {
            api(libs.kotlin.test)
        }
        desktopMain.dependencies {
            api(compose.desktop.currentOs)
            api(libs.kotlinx.coroutinesSwing)
        }
        iosMain.dependencies {

        }
        mobileMain.dependencies {
            api(libs.gitlive.firebase.crashlytics)
        }
        webMain.dependencies {  }
    }
}