package com.madhurmaurya.cuecards.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhurmaurya.cuecards.data.models.CueCard
import com.madhurmaurya.cuecards.databinding.HomeCueCardItemBinding

class CueCardsRecyclerViewAdapter: ListAdapter<CueCard,
        CueCardsRecyclerViewAdapter.CueCardViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<CueCard>() {
        override fun areItemsTheSame(oldItem: CueCard, newItem: CueCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CueCard, newItem: CueCard): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    class CueCardViewHolder(private var binding: HomeCueCardItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(CueCard: CueCard){
            binding.cueCard = CueCard
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CueCardViewHolder {
        return CueCardViewHolder(
            HomeCueCardItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CueCardViewHolder, position: Int) {
        val cueCard = getItem(position)
        holder.bind(cueCard)
    }
}