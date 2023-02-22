import com.example.androidbase.presentation.BuildModules
import com.example.androidbase.presentation.ConfigData
import com.example.androidbase.presentation.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.androidbase"
    compileSdk = ConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = ConfigData.APPLICATION_ID
        minSdk = ConfigData.MIN_SDK_VERSION
        targetSdk = ConfigData.TARGET_SDK_VERSION
        versionCode = ConfigData.VERSION_CODE
        versionName = ConfigData.VERSION_NAME

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(BuildModules.DATA))
    implementation(project(BuildModules.DOMAIN))
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation(Dependencies.ANDROID_MATERIAL)
    implementation(Dependencies.ANDROIDX_CONSTRAINT_LAYOUT)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(Dependencies.TEST_JUNIT)
    androidTestImplementation(Dependencies.TEST_EXPRESO)
    //Navigation component
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    //Dagger - Hilt
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    //Retrofit Dependecies
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.RETROFIT_CONVERTER_MOSHI)
    implementation(Dependencies.RETROFIT_INTERCEPTOR)
    //lifecycle
    implementation(Dependencies.VIEW_MODEL)
    implementation(Dependencies.LIVE_DATA)
    implementation(Dependencies.FRAGMENT_KTS)
    //Room
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KOTLIN_EXTENSION)
    kapt(Dependencies.ROOM_COMPILER)
    //Glide
    implementation(Dependencies.GLIDE)
    annotationProcessor(Dependencies.GLIDE_COMPILER)
}