package com.madhurmaurya.cuecards

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhurmaurya.cuecards.data.models.CueCard
import com.madhurmaurya.cuecards.ui.cueCard.CueCardContentRecyclerViewAdapter
import com.madhurmaurya.cuecards.ui.home.CueCardsRecyclerViewAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CueCard>?){
    val adapter = recyclerView.adapter as CueCardsRecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("cueCard")
fun bindRecyclerViewToCueCard(recyclerView: RecyclerView, data: CueCard?){
    val adapter = recyclerView.adapter as CueCardContentRecyclerViewAdapter
    Log.d("BindingAdapter", data.toString())
    adapter.submitList(data?.contents)
}