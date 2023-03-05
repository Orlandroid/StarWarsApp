package com.example.androidbase

import com.example.androidbase.entities.db.PeopleCache
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    fun getPeople(): Flow<List<PeopleCache>>
}