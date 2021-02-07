package com.romariomkk.destinations.di

import android.content.Context
import androidx.room.Room
import com.romariomkk.destinations.data.db.FlyHiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun db(context: Context) =
        Room
            .databaseBuilder(context, FlyHiDatabase::class.java, FlyHiDatabase.FLYHI_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun destinationsDao(db: FlyHiDatabase) = db.destinationsDao()
}