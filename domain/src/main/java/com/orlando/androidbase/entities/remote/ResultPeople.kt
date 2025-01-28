package com.orlando.androidbase.entities.remote

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

data class People(
    val name: String,
    val gender: String
)

fun ResultPeople.toPeople() = People(name = name, gender = gender)