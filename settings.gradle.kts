pluginManagement {
    repositories {
        // Repositories where you can get 
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://maven.architectury.dev/") }
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.minecraftforge.net/") }
        maven { url = uri("https://maven.blamejared.com/") }
    }
}

rootProject.name = "Hexxy Searches For A Lost Cat In The Alps"
include("Common", "Fabric", "Forge")
