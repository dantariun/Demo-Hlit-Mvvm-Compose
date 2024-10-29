plugins {
    id("dantariun.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dantariun.demo_hilt_mvvm_compose"

    defaultConfig {
        applicationId = "com.dantariun.demo_hilt_mvvm_compose"
        versionCode = 1
        versionName = "1.0"

    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(projects.feature.main)
    implementation(projects.designSystem)
}