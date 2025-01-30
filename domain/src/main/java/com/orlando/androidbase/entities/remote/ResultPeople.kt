package com.orlando.androidbase.entities.remote

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class ResultPeople(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)

@Serializable
data class People(
    val name: String,
    val height: String,
    val gender: String,
    val hairColor: String,
    val mass: String,
    val skinColor: String
)

fun ResultPeople.toPeople() = People(
    name = name,
    height = height,
    gender = gender,
    hairColor = hair_color,
    mass = mass,
    skinColor = skin_color
)

object CustomNavType {

    val peopleType = object : NavType<People>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): People? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): People {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: People): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: People) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}

