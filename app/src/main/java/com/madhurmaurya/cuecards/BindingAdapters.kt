package com.madhurmaurya.cuecards

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhurmaurya.cuecards.data.models.CueCard
import com.madhurmaurya.cuecards.ui.home.CueCardsRecyclerViewAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CueCard>?){
    val adapter = recyclerView.adapter as CueCardsRecyclerViewAdapter
    adapter.submitList(data)
}