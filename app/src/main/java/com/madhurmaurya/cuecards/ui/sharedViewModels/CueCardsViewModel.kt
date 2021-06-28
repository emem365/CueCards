package com.madhurmaurya.cuecards.ui.sharedViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.data.models.CueCard
import com.madhurmaurya.cuecards.data.repository.Repository

class CueCardsViewModel : ViewModel() {
    private val _cueCards = MutableLiveData<List<CueCard>>()
    val cueCards: LiveData<List<CueCard>> get() = _cueCards

    init {
        _cueCards.value = Repository.getAllCueCards()
        Log.d("CueCardsViewModel", "${_cueCards.value}")
        (R.color.pastel_red)
    }

    fun add(cueCard: CueCard) {
        Repository.createCueCard(cueCard)
        _cueCards.value = Repository.getAllCueCards()
    }
}