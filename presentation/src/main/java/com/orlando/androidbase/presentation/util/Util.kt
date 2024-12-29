package com.orlando.androidbase.presentation.util

import com.orlando.androidbase.presentation.util.utilimages.models.ImagesResponse
import com.google.gson.Gson


fun getImageFromJson(name: String, jsonStr: String): String {
    val images = Gson().fromJson(jsonStr, ImagesResponse::class.java)
    images.forEach {
        if (it.name == name) {
            return it.image
        }
    }
    return ""
}



