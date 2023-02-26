package com.example.data.remote


import com.example.domain.entities.remote.PeopleResponseItem
import com.example.domain.entities.remote.ResultPeople
import com.example.domain.entities.remote.ResultResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface Api {

    @GET("people")
    suspend fun getPeople(@Query("page") page: String): ResultResponse

    @GET
    suspend fun getPeopleDetail(@Url url: String): ResultPeople

    @GET("allpeople.json")
    suspend fun getAllPeople(): List<PeopleResponseItem>

}