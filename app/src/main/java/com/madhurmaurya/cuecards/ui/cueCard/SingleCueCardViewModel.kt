package com.madhurmaurya.cuecards.ui.cueCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.madhurmaurya.cuecards.data.models.CueCard
import com.madhurmaurya.cuecards.data.repository.Repository

class SingleCueCardViewModel : ViewModel() {

    private val _card = MutableLiveData<CueCard?>()
    private val _errorMessage = MutableLiveData("")

    val isLoading: LiveData<Boolean>
        get() = Transformations.map(_card) {
            it == null
        }

    val cueCard: LiveData<CueCard?> get() = _card

    val errorMessage: String? get() = _errorMessage.value

    fun getCueCardFromId(id: String) {
        val cueCard = Repository.getCueCardFromId(id)
        _card.value = cueCard.getOrNull()
        _errorMessage.value = cueCard.exceptionOrNull()?.message ?: ""
    }
}