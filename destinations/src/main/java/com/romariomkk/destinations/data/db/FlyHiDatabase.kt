package com.romariomkk.destinations.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.romariomkk.destinations.data.db.pojo.DBDestination
import com.romariomkk.destinations.data.db.pojo.DBDestinationFts

@Database(
    version = 1,
    entities = [DBDestination::class, DBDestinationFts::class]
)
abstract class FlyHiDatabase: RoomDatabase() {

    abstract fun destinationsDao(): DestinationsDao

    companion object {
        const val FLYHI_DB_NAME = "db_flyhi"
    }
}