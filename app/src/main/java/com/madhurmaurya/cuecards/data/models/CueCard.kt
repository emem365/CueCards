package com.madhurmaurya.cuecards.data.models

data class CueCard(
    val id: String,
    val title: String,
    val contents: List<CardContent>,
    val color: Int
)