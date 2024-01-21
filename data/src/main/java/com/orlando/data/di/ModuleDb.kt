package com.orlando.data.di

import android.content.Context
import androidx.room.Room
import com.orlando.data.db.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object ModuleDb {

    private const val DATABASE_NAME = "Database"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePeopleDao(db: MainDatabase) = db.peopleDao()


}