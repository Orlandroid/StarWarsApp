package com.orlando.androidbase.entities.remote

import kotlinx.serialization.Serializable

data class ResultPlanet(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbital_period: String,
    val population: String,
    val residents: List<String>,
    val rotation_period: String,
    val surface_water: String,
    val terrain: String,
    val url: String
)

@Serializable
data class Planet(
    val name: String,
    val population: String,
    val climate: String,
    val terrain: String
)

fun ResultPlanet.toPlanet() =
    Planet(name = name, population = population, climate = climate, terrain = terrain)

