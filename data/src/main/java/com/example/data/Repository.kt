package com.example.data


import android.provider.ContactsContract.Directory.PACKAGE_NAME
import android.util.Log
import com.example.androidbase.LocalDataSource
import com.example.androidbase.RemoteDataSource
import com.example.androidbase.entities.remote.PeopleResponseItem
import com.example.androidbase.mappers.toPeopleCache
import com.example.androidbase.mappers.toPeopleCacheList
import com.example.androidbase.mappers.toPeopleResponseItemList
import javax.inject.Inject


class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPeople(page: String) = remoteDataSource.getPeople(page)
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