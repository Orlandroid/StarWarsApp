package com.example.data.remote


import com.example.androidbase.entities.remote.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {


    @GET
    suspend fun getPeopleDetail(@Url url: String): ResultPeople

    @GET("allpeople.json")
    suspend fun getAllPeople(): List<PeopleResponseItem>

    @GET("people")
    suspend fun getPeople(
        @Query("page") page: String,
        @Query("name") name: String? = null
    ): ResultGeneric<ResultPeople>

    @GET("films")
    suspend fun getFilms(@Query("page") page: String): ResultGeneric<Film>

    @GET("planets")
    suspend fun getPlanets(@Query("page") page: String): ResultGeneric<ResultPlanet>

    @GET("species")
    suspend fun getSpecies(@Query("page") page: String): ResultGeneric<ResultSpecie>

    @GET("starships")
    suspend fun getStarships(@Query("page") page: String): ResultGeneric<ResultStarship>

    @GET("vehicles")
    suspend fun getVehicles(@Query("page") page: String): ResultGeneric<ResultVehicle>


}