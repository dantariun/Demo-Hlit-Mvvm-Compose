pluginManagement {
    includeBuild("build-logic")
    repositories {
        google ()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Demo-Hilt-Mvvm-Compose"
include(":app")
include(":model")
include(":database")
include(":data")
include(":domain")
include(":designSystem")
include(":feature:main")
include(":feature:login")
include(":feature:signup")
include(":feature:admin")
