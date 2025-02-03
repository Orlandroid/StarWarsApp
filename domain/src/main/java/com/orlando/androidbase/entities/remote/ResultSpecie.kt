package com.orlando.androidbase.entities.remote


data class ResultSpecie(
    val average_height: String,
    val average_lifespan: String,
    val classification: String,
    val created: String,
    val designation: String,
    val edited: String,
    val eye_colors: String,
    val films: List<String>,
    val hair_colors: String,
    val homeworld: String,
    val language: String,
    val name: String,
    val people: List<String>,
    val skin_colors: String,
    val url: String
)

data class Specie(
    val name: String,
    val classification: String,
    val skinColor: String,
    val averageLifespan: String
)

fun ResultSpecie.toSpecie() = Specie(
    name = name,
    classification = classification,
    skinColor = skin_colors,
    averageLifespan = average_lifespan
)