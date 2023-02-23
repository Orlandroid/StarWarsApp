package com.example.androidbase.presentation.util

import android.content.Context
import com.example.androidbase.R
import com.example.domain.entities.remote.PeopleResponseItem
import com.google.gson.Gson

fun Context.getItems(): List<PeopleResponseItem> {
    val allPeopleInJson =
        resources.openRawResource(R.raw.allpeople).bufferedReader()
            .use { it.readText() }
    val gson = Gson()
    return gson.fromJson(allPeopleInJson, Array<PeopleResponseItem>::class.java).asList()
}

fun getImagePeopleByName(context: Context, name: String): String? {
    context.getItems().forEach {
        if (name == it.name) {
            return it.image
        }
    }
    return null
}