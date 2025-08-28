package com.example.androidbase.presentation

object Versions {
    const val ANDROID_MATERIAL_VERSION = "1.3.0"
    const val ANDROIDX_CONSTRAINT_LAYOUT_VERSION = "2.0.4"
    const val ANDROIDX_CORE_KTX_VERSION = "1.9.0"
    const val GLIDE_VERSION = "4.14.2"
    const val RETROFIT_VERSION = "2.9.0"
    const val ROOM_VERSION = "2.6.1"
    const val VIEW_MODEL_VERSION = "2.2.0"
    const val LIVE_DATA_VERSION = "2.2.0"
    const val NAVIGATION_VERSION = "2.8.2"
}

object Dependencies {
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL_VERSION}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINT_LAYOUT_VERSION}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTX_VERSION}"
    const val DAGGER_HILT = "com.google.dagger:hilt-android:2.51.1"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:2.51.1"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE_VERSION}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val RETROFIT_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
    const val RETROFIT_CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"
    const val ROOM = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    const val ROOM_KOTLIN_EXTENSION = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.VIEW_MODEL_VERSION}"
    const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIVE_DATA_VERSION}"
    const val FRAGMENT_KTS = "androidx.fragment:fragment-ktx:1.5.5"
    const val TEST_JUNIT = "androidx.test.ext:junit:1.1.4"
    const val TEST_EXPRESO = "androidx.test.espresso:espresso-core:3.5.0"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val CIRCULAR_IMAGE_VIEW = "com.mikhaellopez:circularimageview:4.3.1"
}