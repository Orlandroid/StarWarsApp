package com.example.androidbase

import com.example.androidbase.entities.db.PeopleCache


interface LocalDataSource {
    fun getPeople(): List<PeopleCache>

    suspend fun addPeople(listOfPeople: PeopleCache):Long

    suspend fun addManyPeople(manyPeople: List<PeopleCache>)
}