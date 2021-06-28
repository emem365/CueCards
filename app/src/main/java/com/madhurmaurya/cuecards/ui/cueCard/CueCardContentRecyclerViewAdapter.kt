package com.madhurmaurya.cuecards.ui.cueCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhurmaurya.cuecards.data.models.CardContent
import com.madhurmaurya.cuecards.databinding.CardContentItemBinding

class CueCardContentRecyclerViewAdapter : ListAdapter<CardContent,
        CueCardContentRecyclerViewAdapter.CardContentViewHolder>(DiffCallback) {

    private lateinit var view: View

    companion object DiffCallback : DiffUtil.ItemCallback<CardContent>() {
        override fun areItemsTheSame(oldItem: CardContent, newItem: CardContent): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CardContent, newItem: CardContent): Boolean {
            return oldItem.content == newItem.content
        }
    }

    class CardContentViewHolder(private var binding: CardContentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(CardContent: CardContent, position: Int) {
            val str = "$position."
            binding.cardContent = CardContent
            binding.index = str
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardContentViewHolder {
        view = parent
        return CardContentViewHolder(
            CardContentItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CardContentViewHolder, position: Int) {
        val cardContent = getItem(position)
        holder.bind(cardContent, position)
    }
}