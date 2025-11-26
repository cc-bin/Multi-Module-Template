package convention

import org.gradle.api.Project


internal fun Project.commonConfigureLibraryAndApplication() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.multiplatform")
        apply("org.jetbrains.compose")
        apply("org.jetbrains.kotlin.plugin.compose")
        apply("org.jetbrains.compose.hot-reload")
        apply("io.github.frankois944.spmForKmp")
        apply("org.jetbrains.kotlin.plugin.serialization")
        apply("org.jlleitschuh.gradle.ktlint")
    }

    configureKotlinMultiplatform()


}