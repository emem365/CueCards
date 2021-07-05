package com.madhurmaurya.cuecards.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CueCardDao {

    @Insert
    suspend fun insert(card: CueCard)

    @Query("SELECT * FROM cue_card WHERE id = :id")
    fun getCueCardById(id: Int) : Flow<CueCard>

}