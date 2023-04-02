package com.example.androidbase.entities.remote

data class PlanetsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<ResultPlanet>
)

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