package com.orlando.androidbase.entities.remote

import kotlinx.serialization.Serializable


data class ResultStarship(
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val cost_in_credits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperdrive_rating: String,
    val length: String,
    val manufacturer: String,
    val max_atmosphering_speed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starship_class: String,
    val url: String
)

@Serializable
data class Starship(
    val name: String,
    val manufacturer: String,
    val model: String,
    val maxAtmosphericSpeed: String
)

fun ResultStarship.toStarship() = Starship(
    name = name,
    manufacturer = manufacturer,
    model = model,
    maxAtmosphericSpeed = manufacturer
)