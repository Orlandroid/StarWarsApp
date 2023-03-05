package com.example.androidbase.entities.remote

data class ResultResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultPeople>
)