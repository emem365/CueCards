package com.madhurmaurya.cuecards.data.repository

import com.madhurmaurya.cuecards.data.models.CueCard

object Repository {

    fun getAllCueCards(): List<CueCard>{
        return listOf(
            CueCard("1", "CueCard1"),
            CueCard("2","CueCard2"),
            CueCard("3","CueCard3"),
            CueCard("4","CueCard4"),
            CueCard("5","CueCard5"),
            CueCard("6","CueCard6"),
            CueCard("7","CueCard7"),
            CueCard("8","CueCard8"),
            CueCard("9","CueCard9"),
        )
    }

}