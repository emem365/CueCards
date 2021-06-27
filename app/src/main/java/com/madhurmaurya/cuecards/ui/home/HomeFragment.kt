package com.madhurmaurya.cuecards.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.madhurmaurya.cuecards.databinding.HomeFragmentBinding
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModel

class HomeFragment: Fragment() {

    private val cueCardsViewModel: CueCardsViewModel by activityViewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        binding.cueCardsViewModel = cueCardsViewModel
        binding.cueCardsRecyclerView.adapter = CueCardsRecyclerViewAdapter()
        return binding.root
    }
}