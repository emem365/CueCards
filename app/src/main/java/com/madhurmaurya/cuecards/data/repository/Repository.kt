package com.madhurmaurya.cuecards.data.repository

import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.data.models.CardContent
import com.madhurmaurya.cuecards.data.models.CueCard

object Repository {
    private val cueCards = mutableListOf(
        CueCard(
            "1", "CueCard1",
            listOf(
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_red,
        ),
        CueCard(
            "2", "CueCard2",
            listOf(
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_salmon,
        ),
        CueCard(
            "3", "CueCard3",
            listOf(
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_orange,
        ),
        CueCard(
            "4", "CueCard4",
            listOf(
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_grass,
        ),
        CueCard(
            "5", "CueCard5",
            listOf(
                CardContent("Proin nec auctor erat."),
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus.")
            ),
            R.color.pastel_green,
        ),
        CueCard(
            "6", "CueCard6",
            listOf(
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_purple,
        ),
        CueCard(
            "7", "CueCard7", listOf(
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_grass
        ),
        CueCard(
            "8", "CueCard8",
            listOf(
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_red,
        ),
        CueCard(
            "9", "CueCard9",
            listOf(
                CardContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla id orci a elit luctus dapibus ac at nunc. Praesent convallis nisi id mi lobortis faucibus."),
                CardContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                CardContent("Proin nec auctor erat.")
            ),
            R.color.pastel_red,
        ),
    )

    fun getAllCueCards(): List<CueCard> {
        return cueCards
    }

    fun createCueCard(cueCard: CueCard) {
        cueCards.add(cueCard)
    }

    fun getCueCardFromId(id: String): Result<CueCard> {
        return Result.success(cueCards[0])
    }
}