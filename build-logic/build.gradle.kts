plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "dantariun.android.hilt"
            implementationClass = "com.dantariun.demoApp.HiltAndroidPlugin"
        }
        register("androidRoom") {
            id = "dantariun.android.room"
            implementationClass = "com.dantariun.demoApp.AndroidRoomPlugin"
        }
        register("kotlinHilt"){
            id = "dantariun.kotlin.hilt"
            implementationClass = "com.dantariun.demoApp.HiltKotlinPlugin"
        }
    }
}