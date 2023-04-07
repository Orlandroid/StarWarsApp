package com.example.androidbase.presentation.util

import com.example.androidbase.presentation.util.utilimages.models.ImagesResponse
import com.google.gson.Gson

const val PACKAGE_NAME = "com.example.androidbase"

fun getImageFromJson(name: String, jsonStr: String): String {
    val images = Gson().fromJson(jsonStr, ImagesResponse::class.java)
    images.forEach {
        if (it.name == name) {
            return it.image
        }
    }
    return ""
}

