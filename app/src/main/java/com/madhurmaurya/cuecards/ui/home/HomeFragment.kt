package com.madhurmaurya.cuecards.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.databinding.HomeFragmentBinding
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModel

class HomeFragment : Fragment() {

    private val cueCardsViewModel: CueCardsViewModel by activityViewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.cueCardsViewModel = cueCardsViewModel
        binding.homeFragment = this
        binding.cueCardsRecyclerView.adapter = CueCardsRecyclerViewAdapter()
        return binding.root
    }

    fun addCueCard() {
        findNavController().navigate(R.id.action_homeFragment_to_editCueCardFragment)
    }
}