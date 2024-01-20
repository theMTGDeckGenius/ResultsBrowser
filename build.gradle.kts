// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {

    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"


}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}