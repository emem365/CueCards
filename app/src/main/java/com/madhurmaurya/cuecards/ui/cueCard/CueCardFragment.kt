package com.madhurmaurya.cuecards.ui.cueCard

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
import com.madhurmaurya.cuecards.data.CueCardWithCueCardContents
import com.madhurmaurya.cuecards.databinding.CueCardFragmentBinding
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModel
import com.madhurmaurya.cuecards.ui.sharedViewModels.CueCardsViewModelFactory
import com.madhurmaurya.cuecards.ui.sharedViewModels.EditCueCardViewModel


class CueCardFragment : Fragment() {

    private lateinit var binding: CueCardFragmentBinding
    private val singleCueCardViewModel: SingleCueCardViewModel by activityViewModels{
        val database = (activity?.application as CardsApplication).database
        SingleCueCardViewModelFactory(database.cueCardDao())
    }
    private val cueCardsViewModel: CueCardsViewModel by activityViewModels {
        CueCardsViewModelFactory((activity?.application as CardsApplication).database.cueCardDao())
    }
    private lateinit var card:CueCardWithCueCardContents

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CueCardFragmentBinding.inflate(inflater, container, false)
        binding.color = R.color.pastel_red
        binding.cueCardViewModel = singleCueCardViewModel
        val adapter = CueCardContentRecyclerViewAdapter()
        binding.cueCardFragmentRecyclerView.adapter = adapter


        singleCueCardViewModel.cueCardWithCueCardContents.observe(viewLifecycleOwner){
            binding.color = it.cueCard.color
            binding.textView2.text = it.cueCard.title
            adapter.submitList(it.cueCardContents.map { content -> content.content })
            card = it
        }


        binding.editButton.setOnClickListener { editCueCard() }
        binding.playButton.setOnClickListener { presentCueCard() }
        binding.backButton.setOnClickListener { navigateUp() }
        binding.deleteButton.setOnClickListener { deleteCueCard() }
        return binding.root
    }

    private fun editCueCard() {
        val sharedViewModel: EditCueCardViewModel by activityViewModels()
        Log.d("CueCardFragment", card.toString())
        sharedViewModel.initFromCueCardWithCueCardContents(card)
        findNavController().navigate(R.id.action_cueCardFragment_to_editCueCardFragment)
    }

    private fun deleteCueCard(){
        navigateUp()
        cueCardsViewModel.delete(card)
    }

    private fun presentCueCard(){
        findNavController().navigate(R.id.action_cueCardFragment_to_presentationModeFragment)
    }

    private fun navigateUp(){
        findNavController().navigateUp()
    }
}