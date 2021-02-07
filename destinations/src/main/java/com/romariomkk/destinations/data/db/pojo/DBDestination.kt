package com.romariomkk.destinations.data.db.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.romariomkk.destinations.data.db.DestinationsDao

@Entity(tableName = DestinationsDao.TABLE_DESTINATIONS)
data class DBDestination(
    @PrimaryKey
    val code: String,
    val name: String,
    val countryCode: String,
    val countryName: String
)