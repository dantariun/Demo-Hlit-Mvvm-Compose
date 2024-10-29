plugins {
    id("dantariun.android.feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dantariun.main"
}

dependencies {
    implementation(projects.feature.login)
    implementation(projects.feature.signup)
    implementation(projects.feature.admin)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
}