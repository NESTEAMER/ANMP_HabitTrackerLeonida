// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.8.5"
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    id("com.android.application") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}