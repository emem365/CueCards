package com.madhurmaurya.cuecards.ui.cueCard

import androidx.lifecycle.*
import com.madhurmaurya.cuecards.data.CueCardDao
import com.madhurmaurya.cuecards.data.CueCardWithCueCardContents
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModel
import java.lang.IllegalArgumentException

class SingleCueCardViewModel(
    private val cueCardDao: CueCardDao
) : ViewModel() {

    private lateinit var _cueCardWithCueCardContent: LiveData<List<CueCardWithCueCardContents>>
    val cueCardWithCueCardContents: LiveData<CueCardWithCueCardContents>
        get() = Transformations.map(_cueCardWithCueCardContent) {
            it.first()
        }

    fun reset(id: Long) {
        _cueCardWithCueCardContent = cueCardDao.getCueCardById(id).asLiveData()
    }
}

class SingleCueCardViewModelFactory(
    private val cueCardDao: CueCardDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleCueCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SingleCueCardViewModel(cueCardDao) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
