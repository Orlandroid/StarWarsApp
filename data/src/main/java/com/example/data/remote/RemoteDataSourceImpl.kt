package com.example.data.remote


import com.example.androidbase.RemoteDataSource
import com.example.androidbase.entities.remote.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val api: ApiService
) : RemoteDataSource {
    override suspend fun getPeopleDetail(id: Int): ResultPeople {
        val baseUrl = "https://swapi.dev/api/people/$id"
        return api.getPeopleDetail(baseUrl)
    }

    override suspend fun getPeople(page: String) = api.getPeople(page)
    override suspend fun getAllPeople() = api.getAllPeople()
    override suspend fun getFilms(page: String): FlimsResponse = api.getFilms(page)
    override suspend fun getPlanets(page: String): PlanetsResponse = api.getPlanets(page)
    override suspend fun getSpecies(page: String): SpeciesResponse = api.getSpecies(page)
    override suspend fun getStarships(page: String): StarshipsResponse = api.getStarships(page)
    override suspend fun getVehicles(page: String): VehiclesResponse = api.getVehicles(page)
}