package com.example.domain



import com.example.domain.entities.remote.ResultResponse


interface RemoteDataSource {
    suspend fun getPeople(page: String): ResultResponse
}