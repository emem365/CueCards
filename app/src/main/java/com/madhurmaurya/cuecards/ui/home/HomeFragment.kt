package com.madhurmaurya.cuecards.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.madhurmaurya.cuecards.CardsApplication
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.databinding.HomeFragmentBinding
import com.madhurmaurya.cuecards.ui.cueCard.SingleCueCardViewModel
import com.madhurmaurya.cuecards.ui.cueCard.SingleCueCardViewModelFactory
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModel
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModelFactory

class HomeFragment : Fragment() {

    private val cueCardsViewModel: CueCardsViewModel by activityViewModels {
        CueCardsViewModelFactory((activity?.application as CardsApplication).database.cueCardDao())
    }
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cueCardsViewModel = cueCardsViewModel
        binding.homeFragment = this
        binding.cueCardsRecyclerView.adapter = CueCardsRecyclerViewAdapter{
            onCardClicked(it)
        }
        val adapter = binding.cueCardsRecyclerView.adapter as CueCardsRecyclerViewAdapter
        cueCardsViewModel.cueCards.observe(viewLifecycleOwner){ cueCardWithCueCardContents ->
            adapter.submitList(cueCardWithCueCardContents.map {
                it.cueCard
            })
        }
    }

    fun addCueCard() {
        findNavController().navigate(R.id.action_homeFragment_to_editCueCardFragment)
    }

    private fun onCardClicked(id: Long){
        val singleCueCardViewModel: SingleCueCardViewModel by activityViewModels{
            val database = (activity?.application as CardsApplication).database
            SingleCueCardViewModelFactory(database.cueCardDao())
        }
        singleCueCardViewModel.reset(id)
        findNavController().navigate(R.id.action_homeFragment_to_cueCardFragment)
    }
}