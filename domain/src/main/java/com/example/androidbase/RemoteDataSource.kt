package com.example.androidbase

import com.example.androidbase.entities.remote.PeopleResponseItem
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultResponse


interface RemoteDataSource {
    suspend fun getPeople(page: String): ResultResponse

    suspend fun getPeopleDetail(id: Int): ResultPeople

    suspend fun getAllPeople(): List<PeopleResponseItem>
}