buildscript {
    val kotlin_version by extra("1.8.20")
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        val navigationVersion = "2.4.2"
        val daggerHiltVersion = "2.44"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$daggerHiltVersion")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
