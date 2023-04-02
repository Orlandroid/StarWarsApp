package com.example.androidbase

import com.example.androidbase.entities.remote.*


interface RemoteDataSource {
    suspend fun getPeople(page: String): ResultResponse

    suspend fun getPeopleDetail(id: Int): ResultPeople

    suspend fun getAllPeople(): List<PeopleResponseItem>

    suspend fun getFilms(page: String): FlimsResponse

    suspend fun getPlanets(page: String): PlanetsResponse

    suspend fun getSpecies(page: String): SpeciesResponse

    suspend fun getStarships(page: String): StarshipsResponse

    suspend fun getVehicles(page: String): VehiclesResponse
}