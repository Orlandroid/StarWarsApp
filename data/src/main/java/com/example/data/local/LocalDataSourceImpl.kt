package com.example.data.local


import com.example.androidbase.LocalDataSource
import com.example.androidbase.entities.db.PeopleCache
import com.example.data.db.PeopleDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val userDao: PeopleDao,
) : LocalDataSource {
    override fun getPeople(): List<PeopleCache> = userDao.getAllPeoples()
    override suspend fun addPeople(listOfPeople: PeopleCache) = userDao.addPeople(listOfPeople)
    override suspend fun addManyPeople(manyPeople: List<PeopleCache>) = userDao.insertManyPeople(manyPeople)

}
