package com.orlando.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.orlando.androidbase.entities.db.PeopleCache


@Database(entities = [PeopleCache::class], version = 1, exportSchema = false)

abstract class MainDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

}