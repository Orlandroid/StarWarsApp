package com.example.androidbase.presentation.util

import com.example.androidbase.presentation.util.data.ImagesResponse
import com.google.gson.Gson

const val PACKAGE_NAME = "com.example.androidbase"

fun getImageFromJson(name: String, jsonStr: String): String {
    val images = Gson().fromJson(jsonStr, ImagesResponse::class.java)
    val item = images.filter { item -> item.name == name }[0]
    return item.image
}

