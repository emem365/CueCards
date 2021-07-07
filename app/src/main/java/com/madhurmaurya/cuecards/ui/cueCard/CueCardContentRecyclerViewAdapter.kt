    package com.madhurmaurya.cuecards.ui.cueCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhurmaurya.cuecards.databinding.CardContentItemBinding

class CueCardContentRecyclerViewAdapter : ListAdapter<String,
        CueCardContentRecyclerViewAdapter.CardContentViewHolder>(DiffCallback) {

    private lateinit var view: View

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem:  String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class CardContentViewHolder(private var binding: CardContentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(CueCardContent: String, position: Int) {
            val str = "$position."
            binding.cardContent = CueCardContent
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