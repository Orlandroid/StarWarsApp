package com.example.data



import com.example.androidbase.LocalDataSource
import com.example.androidbase.RemoteDataSource
import com.example.androidbase.entities.remote.PeopleResponseItem
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPeople(page: String) = remoteDataSource.getPeople(page)
    suspend fun getAllPeople(): List<PeopleResponseItem> {
        return remoteDataSource.getAllPeople()
    }

    suspend fun getPeopleDetail(peopleId: Int) = remoteDataSource.getPeopleDetail(peopleId)
}