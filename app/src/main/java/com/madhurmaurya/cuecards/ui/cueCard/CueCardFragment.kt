package com.madhurmaurya.cuecards.ui.cueCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.madhurmaurya.cuecards.R
import com.madhurmaurya.cuecards.databinding.CueCardFragmentBinding
import com.madhurmaurya.cuecards.ui.editCueCard.EditCueCardViewModel

const val CUE_CARD_ID = "cueCard"

class CueCardFragment : Fragment() {

    private lateinit var binding: CueCardFragmentBinding
    private val singleCueCardViewModel: SingleCueCardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CueCardFragmentBinding.inflate(inflater, container, false)
        binding.cueCardViewModel = singleCueCardViewModel
        binding.context = context
        arguments?.let {
            singleCueCardViewModel.getCueCardFromId(
                it.getString(CUE_CARD_ID).toString()
            )
        }
        singleCueCardViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.cueCardFragmentProgressIndicator.visibility = View.VISIBLE
                binding.cueCardFragmentView.visibility = View.GONE
            } else {
                binding.cueCardFragmentProgressIndicator.visibility = View.GONE
                binding.cueCardFragmentView.visibility = View.VISIBLE
                binding.cueCardFragmentRecyclerView.adapter = CueCardContentRecyclerViewAdapter()
            }
        }
        binding.editButton.setOnClickListener { editCueCard() }
        return binding.root
    }

    private fun editCueCard() {
        val sharedViewModel: EditCueCardViewModel by activityViewModels()
        sharedViewModel.initFromCueCard(singleCueCardViewModel.cueCard.value)
        findNavController().navigate(R.id.action_cueCardFragment_to_editCueCardFragment)
    }
}