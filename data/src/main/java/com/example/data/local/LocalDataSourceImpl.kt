package com.example.data.local


import com.example.data.db.PeopleDao
import com.example.domain.LocalDataSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val userDao: PeopleDao,
) : LocalDataSource {

}
