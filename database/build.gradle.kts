plugins {
    id("dantariun.android.library")
    id("dantariun.android.hilt")
    id("dantariun.android.room")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dantariun.database"
}

dependencies {
    implementation(projects.model)
}