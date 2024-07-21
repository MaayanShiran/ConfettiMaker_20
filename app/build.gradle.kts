plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.classy.confettimaker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.classy.confettimaker"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    //implementation(project(":Confetti"))
    implementation(libs.confettilibrary)
    //implementation("com.github.MaayanShiran:ConfettiMakerLibrary:1.00.13")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}