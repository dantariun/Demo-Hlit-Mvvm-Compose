import com.dantariun.demoApp.configureHiltAndroid
import com.dantariun.demoApp.libs

plugins {
    id("dantariun.android.library")
    id("dantariun.android.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()

dependencies {
    implementation(project(":model"))
    implementation(project(":designSystem"))
    implementation(project(":domain"))

    val libs = project.extensions.libs
    implementation(libs.findLibrary("hilt.navigation.compose").get())
    implementation(libs.findLibrary("androidx.compose.navigation").get())
    androidTestImplementation(libs.findLibrary("androidx.compose.navigation.test").get())

    implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
}