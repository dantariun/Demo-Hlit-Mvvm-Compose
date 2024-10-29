plugins {
    id("dantariun.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dantariun.domain"
}

dependencies {
    implementation(projects.data)
    implementation(projects.model)
}