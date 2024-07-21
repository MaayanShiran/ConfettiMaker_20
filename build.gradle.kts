// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.0")  // Update to your AGP version
        // Add other dependencies as needed
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
