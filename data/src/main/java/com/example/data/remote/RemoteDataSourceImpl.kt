package com.example.data.remote


import com.example.domain.RemoteDataSource
import com.example.domain.entities.remote.PeopleResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteDataSource {
    override suspend fun getPeople(page: String) = api.getPeople(page)
    override suspend fun getAllPeople() = api.getAllPeople()
}