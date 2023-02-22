package com.example.data


import com.example.domain.LocalDataSource
import com.example.domain.RemoteDataSource
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPeople(page: String) = remoteDataSource.getPeople(page)
    suspend fun getAllPeople() = remoteDataSource.getAllPeople()
}