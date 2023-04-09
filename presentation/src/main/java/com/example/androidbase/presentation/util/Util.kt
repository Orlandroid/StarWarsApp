package com.example.androidbase.presentation.util

import com.example.androidbase.presentation.util.utilimages.models.ImagesResponse
import com.google.gson.Gson

const val PACKAGE_NAME = "com.example.presentation"

fun getImageFromJson(name: String, jsonStr: String): String {
    val images = Gson().fromJson(jsonStr, ImagesResponse::class.java)
    images.forEach {
        if (it.name == name) {
            return it.image
        }
    }
    return ""
}

fun getCurrentPage(pageInUrl: String?): Int? {
    if (pageInUrl == null) {
        return null
    }
    return pageInUrl.split("=")[1].toInt()
}



