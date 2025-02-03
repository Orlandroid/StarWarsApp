package com.orlando.androidbase.entities.remote

import kotlinx.serialization.Serializable

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


