import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.Packaging
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class ComposeMultiplatformPlugin : Plugin<Project> {
    @OptIn(ExperimentalWasmDsl::class)
    override fun apply(project: Project) {
        project.plugins.apply("org.jetbrains.kotlin.multiplatform")
        project.plugins.apply("org.jetbrains.compose")
        project.plugins.apply("org.jetbrains.kotlin.plugin.compose")
        project.plugins.apply("org.jetbrains.compose.hot-reload")
        project.plugins.apply("io.github.frankois944.spmForKmp")
        project.plugins.apply("org.jetbrains.kotlin.plugin.serialization")

        // Kotlin Multiplatform 配置
        val kotlinExt = project.extensions.getByName("kotlin") as KotlinMultiplatformExtension

        kotlinExt.apply {
            androidTarget {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }

            listOf(
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget: KotlinNativeTarget ->
                iosTarget.binaries.framework {
                    baseName = "ComposeApp"
                    isStatic = true
                }
            }

            jvm()

            js {
                browser()
                binaries.executable()
            }

            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                browser()
                binaries.executable()
            }
        }

        // Android 配置
        val androidExt = project.extensions.getByName("android") as CommonExtension<*, *, *, *, *, *>
        androidExt.apply {
            compileSdk = 36

            defaultConfig {
                minSdk = 24
            }


            compileOptions.apply {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }
}
