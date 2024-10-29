plugins {
    id("dantariun.android.library")
    id("dantariun.android.hilt")
    id("dantariun.kotlin.hilt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dantariun.data"
}

dependencies {
    implementation(projects.model)
    implementation(projects.database)
}