package com.madhurmaurya.cuecards.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cue_card")
data class CueCard(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val color: Int
)
