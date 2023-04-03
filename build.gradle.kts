// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Dependencies.gradlePlugin) version (Versions.gradlePlugin) apply false
    id(Dependencies.gradlePluginLibrary) version (Versions.gradlePlugin) apply false
    id(Dependencies.kotlinAndroid) version (Versions.kotlinAndroid) apply false
    id(Dependencies.hiltPlugin) version (Versions.hilt) apply false
}