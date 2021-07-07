package com.madhurmaurya.cuecards

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhurmaurya.cuecards.data.CueCardContent
import com.madhurmaurya.cuecards.data.CueCardWithCueCardContents
import com.madhurmaurya.cuecards.ui.cueCard.CueCardContentRecyclerViewAdapter

@BindingAdapter("cueCard")
fun bindRecyclerViewToCueCard(recyclerView: RecyclerView, data: CueCardWithCueCardContents?){
    if(data == null){
        return
    }
    val adapter = recyclerView.adapter as CueCardContentRecyclerViewAdapter
    val contents = data.cueCardContents
    adapter.submitList(contents.map { it.content })
}