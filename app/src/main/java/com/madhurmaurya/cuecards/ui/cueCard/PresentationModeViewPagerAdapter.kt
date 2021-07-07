package com.madhurmaurya.cuecards.ui.cueCard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.madhurmaurya.cuecards.data.CueCardContent
import com.madhurmaurya.cuecards.databinding.PresentationModeItemFragmentBinding

class PresentationModeViewPagerAdapter(private val viewPager: ViewPager2) : ListAdapter<CueCardContent,
        PresentationModeViewPagerAdapter.CardContentViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<CueCardContent>() {
        override fun areItemsTheSame(oldItem: CueCardContent, newItem: CueCardContent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CueCardContent, newItem: CueCardContent): Boolean {
            return oldItem.content == newItem.content
        }
    }

    class CardContentViewHolder(private var binding: PresentationModeItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(CueCardContent: CueCardContent, viewPager: ViewPager2) {
            binding.cardContent = CueCardContent
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