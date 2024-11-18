import com.android.build.gradle.LibraryExtension
import com.development.buildlogic.convention.ExtensionType
import com.development.buildlogic.convention.configureAndroidCompose
import com.development.buildlogic.convention.configureBuildTypes
import com.development.buildlogic.convention.configureKotlinAndroid
import com.development.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                configureBuildTypes(this, ExtensionType.LIBRARY)

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                dependencies {
                    "implementation"(libs.findLibrary("hilt").get())
                    "ksp"(libs.findLibrary("hilt.compiler").get())
                    "implementation"(libs.findLibrary("hilt.navigation.compose").get())
                    "testImplementation"(kotlin("test"))
                }
            }
        }
    }
}