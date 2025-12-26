pluginManagement {
    repositories {
        google()
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

rootProject.name = "playstore-clone-test"

include(":application")
include(":core:domain")
include(":core:data")
include(":feature:appcatalog")
include(":feature:addapplication")
include(":feature:applicationdetail")
