package com.romariomkk.destinations.data.db.pojo

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.romariomkk.destinations.data.db.DestinationsDao

@Fts4(contentEntity = DBDestination::class)
@Entity(tableName = DestinationsDao.TABLE_DESTINATIONS_FTS)
data class DBDestinationFts(
    @PrimaryKey
    val rowid: Int,
    val code: String,
    val name: String,
    val countryCode: String,
    val countryName: String
)