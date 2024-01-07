package com.example.androidbase

import com.example.androidbase.entities.remote.*


interface RemoteDataSource {
    suspend fun getPeople(page: String): ResultGeneric<ResultPeople>

    suspend fun getPeopleDetail(id: Int): ResultPeople

    suspend fun getAllPeople(): List<PeopleResponseItem>

    suspend fun getFilms(page: String): ResultGeneric<Film>

    suspend fun getPlanets(page: String): ResultGeneric<ResultPlanet>

    suspend fun getSpecies(page: String): ResultGeneric<ResultSpecie>

    suspend fun getStarships(page: String): ResultGeneric<ResultStarship>

    suspend fun getVehicles(page: String): ResultGeneric<ResultVehicle>
}