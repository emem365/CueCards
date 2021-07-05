package com.madhurmaurya.cuecards.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_contents")
data class CueCardContent(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "cue_card_id")
    val cueCardId: Int,
    val content: String
)