import com.dantariun.demoApp.configureHiltAndroid
import com.dantariun.demoApp.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureHiltAndroid()
