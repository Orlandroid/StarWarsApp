package com.example.data


import com.example.androidbase.LocalDataSource
import com.example.androidbase.RemoteDataSource
import com.example.androidbase.entities.remote.*
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPeople(page: String) = remoteDataSource.getPeople(page)

    suspend fun getFilms(page: String) = remoteDataSource.getFilms(page)

    suspend fun getPlanets(page: String): PlanetsResponse = remoteDataSource.getPlanets(page)

    suspend fun getSpecies(page: String): SpeciesResponse = remoteDataSource.getSpecies(page)

    suspend fun getStarships(page: String): StarshipsResponse = remoteDataSource.getStarships(page)

    suspend fun getVehicles(page: String): VehiclesResponse = remoteDataSource.getVehicles(page)


    suspend fun getAllPeople(): List<PeopleResponseItem> {
        return remoteDataSource.getAllPeople()
        /*
        val peopleFromCache = localDataSource.getPeople()
        if (peopleFromCache.isNotEmpty()) {
            Log.w(PACKAGE_NAME, peopleFromCache.size.toString())
            return peopleFromCache.toPeopleResponseItemList()
        }
        val peopleFromApi = remoteDataSource.getAllPeople()
        if (peopleFromCache.isEmpty()) {
            peopleFromApi.forEach { peopleApi ->
                val insert = localDataSource.addPeople(peopleApi.toPeopleCache())
                Log.w(PACKAGE_NAME, insert.toString())
            }
        }
        return peopleFromApi*/
    }

    suspend fun getPeopleDetail(peopleId: Int) = remoteDataSource.getPeopleDetail(peopleId)
}