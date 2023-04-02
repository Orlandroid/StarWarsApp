package com.example.data.remote


import com.example.androidbase.entities.remote.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {

    @GET("people")
    suspend fun getPeople(@Query("page") page: String): ResultResponse

    @GET
    suspend fun getPeopleDetail(@Url url: String): ResultPeople

    @GET("allpeople.json")
    suspend fun getAllPeople(): List<PeopleResponseItem>

    @GET("films")
    suspend fun getFilms(@Query("page") page: String): FlimsResponse

    @GET("planets")
    suspend fun getPlanets(@Query("page") page: String): PlanetsResponse

    @GET("species")
    suspend fun getSpecies(@Query("page") page: String): SpeciesResponse

    @GET("starships")
    suspend fun getStarships(@Query("page") page: String): StarshipsResponse

    @GET("vehicles")
    suspend fun getVehicles(@Query("page") page: String): VehiclesResponse


}