package com.madhurmaurya.cuecards.ui.cueCard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.madhurmaurya.cuecards.data.models.CardContent
import com.madhurmaurya.cuecards.databinding.PresentationModeItemFragmentBinding

class PresentationModeViewPagerAdapter(private val viewPager: ViewPager2) : ListAdapter<CardContent,
        PresentationModeViewPagerAdapter.CardContentViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<CardContent>() {
        override fun areItemsTheSame(oldItem: CardContent, newItem: CardContent): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CardContent, newItem: CardContent): Boolean {
            return oldItem.content == newItem.content
        }
    }

    class CardContentViewHolder(private var binding: PresentationModeItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(CardContent: CardContent, viewPager: ViewPager2) {
            binding.cardContent = CardContent
            binding.root.setOnClickListener {
                viewPager.apply {
                    beginFakeDrag()
                    fakeDragBy(-10f)
                    endFakeDrag()
                }
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardContentViewHolder {
        return CardContentViewHolder(
            PresentationModeItemFragmentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CardContentViewHolder, position: Int) {
        val cueCard = getItem(position)
        holder.bind(cueCard, viewPager)
    }
}