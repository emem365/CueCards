package com.madhurmaurya.cuecards.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhurmaurya.cuecards.data.models.CueCard
import com.madhurmaurya.cuecards.databinding.HomeCueCardItemBinding

class CueCardsRecyclerViewAdapter : ListAdapter<CueCard,
        CueCardsRecyclerViewAdapter.CueCardViewHolder>(DiffCallback) {

    private lateinit var view: View

    companion object DiffCallback : DiffUtil.ItemCallback<CueCard>() {
        override fun areItemsTheSame(oldItem: CueCard, newItem: CueCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CueCard, newItem: CueCard): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    class CueCardViewHolder(private var binding: HomeCueCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(CueCard: CueCard, view: View) {
            binding.cueCard = CueCard
            binding.context = view.context
            binding.root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCueCardFragment(
                    CueCard.id
                )
                view.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CueCardViewHolder {
        view = parent
        return CueCardViewHolder(
            HomeCueCardItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CueCardViewHolder, position: Int) {
        val cueCard = getItem(position)
        holder.bind(cueCard, view)
    }
}