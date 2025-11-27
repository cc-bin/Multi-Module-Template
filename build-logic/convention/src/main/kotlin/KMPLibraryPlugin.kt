import com.android.build.api.dsl.LibraryExtension
import convention.commonConfigureLibraryAndApplication
import convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

class KMPLibraryPlugin : Plugin<Project> {
    @OptIn(ExperimentalWasmDsl::class)
    override fun apply(project: Project) {
        with(project){
            pluginManager.apply("com.android.library")
            commonConfigureLibraryAndApplication()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix = path
                    .split("""\W""".toRegex())
                    .drop(1).distinct()
                    .joinToString(separator = "_")
                    .lowercase() + "_"
            }
        }
    }
}
