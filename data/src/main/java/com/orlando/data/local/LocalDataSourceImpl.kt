package com.orlando.data.local


import com.orlando.androidbase.LocalDataSource
import com.orlando.androidbase.entities.db.PeopleCache
import com.orlando.data.db.PeopleDao
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
