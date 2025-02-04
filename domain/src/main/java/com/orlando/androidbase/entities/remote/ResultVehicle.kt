package com.orlando.androidbase.entities.remote

import kotlinx.serialization.Serializable


data class ResultVehicle(
    val cargo_capacity: String,
    val consumables: String,
    val cost_in_credits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val length: String,
    val manufacturer: String,
    val max_atmosphering_speed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val url: String,
    val vehicle_class: String
)

@Serializable
data class Vehicle(
    val name: String,
    val manufacturer: String,
    val maxAtmospheringSpeed: String,
    val consumables: String
)


fun ResultVehicle.toVehicle() = Vehicle(
    name = name,
    manufacturer = manufacturer,
    maxAtmospheringSpeed = max_atmosphering_speed,
    consumables = consumables
)