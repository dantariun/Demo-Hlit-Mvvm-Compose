plugins {
    id("dantariun.android.library")
    id("dantariun.android.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dantariun.designsystem"
}

dependencies {
    implementation(libs.androidx.appcompat)
}