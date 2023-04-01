package com.example.data.db

import androidx.room.*
import com.example.androidbase.entities.db.PeopleCache
import kotlinx.coroutines.flow.Flow


@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPeople(user: PeopleCache): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyPeople(manyPeople: List<PeopleCache>)

    @Update
    suspend fun updatePeople(user: PeopleCache)

    @Query("SELECT * FROM PeopleCache")
    fun getAllPeople(): Flow<List<PeopleCache>>

    @Query("SELECT * FROM PeopleCache")
    fun getAllPeoples(): List<PeopleCache>

    @Query("SELECT * FROM peoplecache WHERE id =:id")
    fun getPeopleById(id: Int): Flow<PeopleCache>

    @Query("DELETE  FROM peoplecache")
    suspend fun deleteAll()

}