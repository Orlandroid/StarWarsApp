package com.example.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidbase.entities.db.PeopleCache


@Database(entities = [PeopleCache::class], version = 1, exportSchema = false)

abstract class MainDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

}