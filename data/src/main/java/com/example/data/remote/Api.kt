package com.example.data.remote


import com.example.domain.entities.remote.ResultResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("people")
    suspend fun getPeople(@Query("page") page: String): ResultResponse

}