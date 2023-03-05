package com.example.data.local


import com.example.androidbase.LocalDataSource
import com.example.androidbase.entities.db.PeopleCache
import com.example.data.db.PeopleDao
import kotlinx.coroutines.flow.Flow


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val userDao: PeopleDao,
) : LocalDataSource {
    override fun getPeople(): Flow<List<PeopleCache>> = userDao.getAllPeople()

}
