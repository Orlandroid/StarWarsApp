package com.example.domain


import com.example.domain.entities.remote.PeopleResponseItem
import com.example.domain.entities.remote.ResultPeople
import com.example.domain.entities.remote.ResultResponse


interface RemoteDataSource {
    suspend fun getPeople(page: String): ResultResponse

    suspend fun getPeopleDetail(id: Int): ResultPeople

    suspend fun getAllPeople(): List<PeopleResponseItem>
}