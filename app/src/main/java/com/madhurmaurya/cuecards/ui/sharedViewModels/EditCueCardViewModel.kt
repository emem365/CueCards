package com.madhurmaurya.cuecards.ui.sharedViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.data.models.CardContent
import com.madhurmaurya.cuecards.data.models.CueCard

val colorsList = listOf(
    R.color.pastel_grass,
    R.color.pastel_green,
    R.color.pastel_orange,
    R.color.pastel_purple,
    R.color.pastel_red,
    R.color.pastel_salmon
)

class EditCueCardViewModel : ViewModel() {
    private var _contentList = mutableListOf<CardContent>()
    private val _contents = MutableLiveData<List<CardContent>>(listOf())
    private var randomColor = colorsList.random()
    private var _title: String = ""
    private var _id: String = ""

    val currentState: LiveData<CueCard>
        get() = Transformations.map(_contents) {
            CueCard(_id, _title, it, randomColor)
        }

    val title: String get() = _title
    fun add(content: CardContent) {
        _contentList.add(content)
        _contents.value = _contentList
    }

    fun setTitle(t: String) {
        _title = t
        Log.d("EditCueCardVM", _title)
    }

    fun reset() {
        _contentList = mutableListOf()
        _contents.value = listOf()
        randomColor = colorsList.random()
        _title = ""
    }

    fun getCueCard(): CueCard? {
        if (_contents.value != null && _contents.value!!.isNotEmpty()) {
            if (_title != "") {
                return CueCard(_id, _title, _contents.value!!, randomColor)
            }
        }
        return null
    }

    fun initFromCueCard(cueCard: CueCard?) {
        if (cueCard == null) {
            return
        }
        _id = cueCard.id
        _title = cueCard.title
        _contentList = cueCard.contents.toMutableList()
        _contents.value = _contentList
        randomColor = cueCard.color
    }
}

