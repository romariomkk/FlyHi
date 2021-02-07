package com.romariomkk.destinations.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.romariomkk.destinations.data.db.pojo.DBDestination

@Dao
abstract class DestinationsDao {

    suspend fun isAnyItemAvailable() =
        getItemCount() > 0

    @Insert
    abstract suspend fun saveAll(destinations: List<DBDestination>)

    suspend fun search(query: String): List<DBDestination> {
        return if (query.isEmpty()) {
            getAll()
        } else {
            _search("*$query*")
        }
    }



    @Query("SELECT COUNT(*) FROM $TABLE_DESTINATIONS")
    protected abstract suspend fun getItemCount(): Int

    @Query(
        """
        SELECT * FROM $TABLE_DESTINATIONS
        JOIN $TABLE_DESTINATIONS_FTS ON $TABLE_DESTINATIONS.rowid = $TABLE_DESTINATIONS_FTS.rowid
        WHERE $TABLE_DESTINATIONS_FTS MATCH :searchInput
        ORDER BY countryName
        """)
    protected abstract suspend fun _search(searchInput: String): List<DBDestination>

    @Query("SELECT * FROM $TABLE_DESTINATIONS ORDER BY countryName")
    protected abstract suspend fun getAll(): List<DBDestination>

    companion object {
        const val TABLE_DESTINATIONS = "dests"
        const val TABLE_DESTINATIONS_FTS = "dests_fts"
    }
}