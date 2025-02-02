package com.orlando.androidbase.entities.remote

import kotlinx.serialization.Serializable


data class Film(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episode_id: Int,
    val opening_crawl: String,
    val planets: List<String>,
    val producer: String,
    val release_date: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)

@Serializable
data class Movie(
    val title: String,
    val producer: String,
    val release: String
)

fun Film.toMovie() = Movie(
    title = title,
    producer = producer,
    release = release_date
)