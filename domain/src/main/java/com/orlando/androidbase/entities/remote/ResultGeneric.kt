package com.orlando.androidbase.entities.remote

data class ResultGeneric<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)