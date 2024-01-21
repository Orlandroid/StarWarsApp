package com.orlando.androidbase

import com.orlando.androidbase.entities.remote.Film
import com.orlando.androidbase.entities.remote.PeopleResponseItem
import com.orlando.androidbase.entities.remote.ResultGeneric
import com.orlando.androidbase.entities.remote.ResultPeople
import com.orlando.androidbase.entities.remote.ResultPlanet
import com.orlando.androidbase.entities.remote.ResultSpecie
import com.orlando.androidbase.entities.remote.ResultStarship
import com.orlando.androidbase.entities.remote.ResultVehicle


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