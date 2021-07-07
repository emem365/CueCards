package com.madhurmaurya.cuecards.data

import androidx.room.Embedded
import androidx.room.Relation

data class CueCardWithCueCardContents(
    @Embedded val cueCard: CueCard,
    @Relation(
        parentColumn = "id",
        entityColumn = "cue_card_id",
    ) val cueCardContents: List<CueCardContent>
)