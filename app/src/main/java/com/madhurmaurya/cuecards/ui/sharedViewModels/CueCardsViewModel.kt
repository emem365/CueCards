package com.madhurmaurya.cuecards.ui.sharedViewModels

import android.util.Log
import androidx.lifecycle.*
import com.madhurmaurya.cuecards.data.CueCard
import com.madhurmaurya.cuecards.data.CueCardContent
import com.madhurmaurya.cuecards.data.CueCardDao
import com.madhurmaurya.cuecards.data.CueCardWithCueCardContents
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CueCardsViewModel(private val cueCardDao: CueCardDao) : ViewModel() {
    val cueCards: LiveData<List<CueCardWithCueCardContents>> = cueCardDao.getAllCards().asLiveData()

    fun add(t: String, color: Int, contents: List<String>) {
        viewModelScope.launch {
            val cueCardId = cueCardDao.insertCueCard(
                CueCard(title = t, color= color)
            )
            contents.forEach {
                cueCardDao.insertCueCardContent(
                    CueCardContent(cueCardId = cueCardId, content = it)
                )
            }
        }
    }

    fun update(id: Long, t: String, color: Int, contents: List<String>){
        viewModelScope.launch {
            Log.d("Coroutine", "start")
            cueCardDao.updateCueCard(
                CueCard(id = id, title = t, color= color)
            )
            Log.d("Coroutine", "Update CueCard")
            val list = cueCardDao.getCueCardContentsById(id)
            Log.d("Coroutine", list.toString())
            list.forEach { cueCardDao.deleteCueCardContent(it) }
            Log.d("Coroutine", "Deleted")
            contents.forEach {
                cueCardDao.insertCueCardContent(
                    CueCardContent(cueCardId = id, content = it)
                )
            }
            Log.d("Coroutine", "Inserted")
        }
    }

    fun delete(cueCardWithCueCardContents: CueCardWithCueCardContents){
        viewModelScope.launch {
            cueCardDao.deleteCueCard(cueCardWithCueCardContents.cueCard)
            cueCardWithCueCardContents.cueCardContents.forEach {
                cueCardDao.deleteCueCardContent(it)
            }
        }
    }
}

class CueCardsViewModelFactory(private val cueCardDao: CueCardDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CueCardsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CueCardsViewModel(cueCardDao) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}