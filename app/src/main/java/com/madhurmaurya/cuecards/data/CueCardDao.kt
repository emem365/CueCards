package com.madhurmaurya.cuecards.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CueCardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCueCard(card: CueCard): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCueCardContent(cueCardContent: CueCardContent)

    @Transaction
    @Query("SELECT * FROM cue_card")
    fun getAllCards(): Flow<List<CueCardWithCueCardContents>>

    @Transaction
    @Query("SELECT * FROM cue_card WHERE id = :id")
    fun getCueCardById(id: Long) : Flow<List<CueCardWithCueCardContents>>

    @Query("SELECT * FROM card_contents WHERE cue_card_id = :cueCardId")
    suspend fun getCueCardContentsById(cueCardId: Long): List<CueCardContent>

    @Update
    suspend fun updateCueCard(card: CueCard)

    @Update
    suspend fun updateCueCardContent(cueCardContent: CueCardContent)

    @Delete
    suspend fun deleteCueCard(card: CueCard)

    @Delete
    suspend fun deleteCueCardContent(cueCardContent: CueCardContent)

}