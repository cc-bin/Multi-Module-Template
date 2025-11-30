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
            api(libs.koin.android)

            api(project.dependencies.platform(libs.firebase.bom))
            api(libs.firebase.analytics)
            api(libs.firebase.crashlytics.ndk)
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
            api(libs.navigation.compose)

            api(libs.kotlinx.coroutines.core)

            api(libs.koin.ktor)
            api(libs.koin.core)
            api(libs.koin.test)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)


            implementation(libs.store)

            api(libs.coil.compose)
            implementation(libs.coil.network.ktor3)

            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.flow)
            api(libs.ktor.client.logging)
            api(libs.kermit)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.content.negotiation)
            api(libs.ktor.serialization.kotlinx.json)

            implementation(libs.connectivity.core)

            api(libs.filekit.core)
            api(libs.filekit.dialogs.compose)
            api(libs.filekit.coil)
        }
        commonTest.dependencies {
            api(libs.kotlin.test)
        }
        desktopMain.dependencies {
            api(compose.desktop.currentOs)
            api(libs.kotlinx.coroutinesSwing)

            api(libs.calf.webview)
        }
        iosMain.dependencies {

        }
        mobileMain.dependencies {
            api(libs.gitlive.firebase.crashlytics)
            api(libs.calf.permissions)
            api(libs.calf.webview)

            api(libs.connectivity.device)
        }
        desktopMain.dependencies {
            api(libs.connectivity.http)
        }
    }
}