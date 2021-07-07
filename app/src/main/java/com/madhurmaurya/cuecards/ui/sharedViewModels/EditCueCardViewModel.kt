package com.madhurmaurya.cuecards.ui.sharedViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.data.CueCardWithCueCardContents

val colorsList = listOf(
    R.color.pastel_grass,
    R.color.pastel_green,
    R.color.pastel_orange,
    R.color.pastel_purple,
    R.color.pastel_red,
    R.color.pastel_salmon
)

class EditCueCardViewModel : ViewModel() {

    private var _contentList = mutableListOf<String>()
    private val _contents = MutableLiveData<List<String>>(listOf())
    val contents: LiveData<List<String>> get() = _contents

    private var _title: String = ""
    val title: String get() = _title

    private var _randomColor = colorsList.random()
    val color: Int get() = _randomColor

    private var _id: Long? = null
    val id: Long? get() = _id


    fun add(content: String) {
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
        _randomColor = colorsList.random()
        _title = ""
        _id = null
    }


    fun initFromCueCardWithCueCardContents(cueCardWithCueCardContents: CueCardWithCueCardContents?) {
        if (cueCardWithCueCardContents== null) {
            return
        }

        _id = cueCardWithCueCardContents.cueCard.id
        _title = cueCardWithCueCardContents.cueCard.title
        _contentList = cueCardWithCueCardContents.cueCardContents.map { it.content }.toMutableList()
        _contents.value = _contentList
        _randomColor = cueCardWithCueCardContents.cueCard.color
    }
}

